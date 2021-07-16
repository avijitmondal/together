package com.avijitmondal.together.alive.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliveControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void sessionIdTest() throws Exception {
        var resultActions = mockMvc.perform(get("/session_id"))
                .andExpect(status().isOk());
        String responseBody = resultActions.andReturn().getResponse().getContentAsString();
        assert Integer.parseInt(responseBody) > 0;
    }
}