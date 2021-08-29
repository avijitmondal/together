package com.avijitmondal.together.auth.controller;

import com.avijitmondal.together.auth.model.Authority;
import com.avijitmondal.together.auth.model.User;
import com.avijitmondal.together.auth.model.bean.AuthorityRole;
import com.avijitmondal.together.auth.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SecuredControllerTest {

    private final String unauthorizedResponse = "{\"error\":\"unauthorized\",\"error_description\":\"Full authentication is required to access this resource\"}";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private UserRepository localMockRepository;

    private String obtainOAuthAccessToken(String username, String password) throws Exception {
        final var CONTENT_TYPE = "application/x-www-form-urlencoded; charset=utf-8";

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
        Authority authorityAdmin = new Authority();
        authorityAdmin.setId(1L);
        authorityAdmin.setRole(AuthorityRole.ROLE_ADMIN);

        Authority authorityUser = new Authority();
        authorityUser.setId(2L);
        authorityUser.setRole(AuthorityRole.ROLE_USER);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("admin");
        user.setEmail("admin@example.com");
        user.setPassword("$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri");
        user.setEnabled(true);
        user.setAuthorities(Set.of(authorityAdmin, authorityUser));
        localMockRepository.save(user);

        var accessToken = obtainOAuthAccessToken("admin", "password");

        Assertions.assertNotNull(accessToken);

        var resultActions = mockMvc.perform(get("/secured/admin").header(HttpHeaders.AUTHORIZATION,
                "Bearer " + accessToken))
                .andExpect(status().isOk());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        assert responseBody
                .contains("This resource is secured. Authentication: admin; Authorities:");
        assert responseBody
                .contains("ROLE_USER");
        assert responseBody
                .contains("ROLE_ADMIN");
    }

    @Test
    void securedResourceAsUser() throws Exception {
        Authority authority = new Authority();
        authority.setId(2L);
        authority.setRole(AuthorityRole.ROLE_USER);

        User user = new User();
        user.setId(UUID.randomUUID());
        user.setUsername("user1");
        user.setEmail("user1@example.com");
        user.setPassword("$2a$10$D4OLKI6yy68crm.3imC9X.P2xqKHs5TloWUcr6z5XdOqnTrAK84ri");
        user.setEnabled(true);
        user.setAuthorities(Set.of(authority));
        localMockRepository.save(user);

        var accessToken = obtainOAuthAccessToken("user1", "password");

        Assertions.assertNotNull(accessToken);

        mockMvc.perform(get("/secured/user").header(HttpHeaders.AUTHORIZATION,
                "Bearer " + accessToken))
                .andExpect(status().isOk())
                .andExpect(content().string("This resource is secured. Authentication: user1; Authorities: [ROLE_USER]"));
    }

    @Test
    void UnauthorizedTest() throws Exception {
        mockMvc.perform(get("/secured/user"))
                .andExpect(status().isUnauthorized())
                .andExpect(content()
                        .json(unauthorizedResponse));
    }
}
