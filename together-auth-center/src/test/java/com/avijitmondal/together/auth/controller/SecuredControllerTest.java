package com.avijitmondal.together.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.Base64Utils;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class SecuredControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void securedResourceAsAdmin() throws Exception {
        mockMvc.perform(get("/secured").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("admin:P@ssw0rd".getBytes())))
                .andExpect(status().isOk())
                .andExpect(content().string("This resource is secured. Authentication: admin; Authorities: [ROLE_ADMIN, ROLE_USER]"));
    }

    @Test
    void securedResourceAsUser() throws Exception {
        mockMvc.perform(get("/secured").header(HttpHeaders.AUTHORIZATION,
                "Basic " + Base64Utils.encodeToString("user:P@ssw0rd".getBytes())))
                .andExpect(status().isOk())
                .andExpect(content().string("This resource is secured. Authentication: user; Authorities: [ROLE_USER]"));
    }
}