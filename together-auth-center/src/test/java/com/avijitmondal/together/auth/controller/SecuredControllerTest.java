package com.avijitmondal.together.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String obtainOAuthAccessToken(String username, String password) throws Exception {
        final var CONTENT_TYPE = "application/json;charset=UTF-8";

        final MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "myclient");
        params.add("username", username);
        params.add("password", password);

        var resultActions = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic("myclient", "secret"))
                .accept(CONTENT_TYPE))
                .andExpect(status().isOk());

        var resultString = resultActions.andReturn().getResponse().getContentAsString();
        var jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }

    @Test
    void securedResourceAsAdmin() throws Exception {
        var accessToken = obtainOAuthAccessToken("admin", "password");
        var resultActions = mockMvc.perform(get("/secured/admin").header(HttpHeaders.AUTHORIZATION,
                "Bearer " + accessToken))
                .andExpect(status().isOk());
        assert resultActions.andReturn().getResponse().getContentAsString()
                .contains("This resource is secured. Authentication: admin; Authorities:");
    }

    @Test
    void securedResourceAsUser() throws Exception {
        var accessToken = obtainOAuthAccessToken("user1", "password");
        mockMvc.perform(get("/secured/user").header(HttpHeaders.AUTHORIZATION,
                "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string("This resource is secured. Authentication: user1; Authorities: [ROLE_USER]"));
    }

    @Test
    public void UnauthorizedTest() throws Exception {
        mockMvc.perform(get("/secured/user"))
                .andExpect(status().isUnauthorized())
                .andExpect(content().json("{\"error\":\"unauthorized\",\"error_description\":\"Full authentication is required to access this resource\"}"));
    }
}
