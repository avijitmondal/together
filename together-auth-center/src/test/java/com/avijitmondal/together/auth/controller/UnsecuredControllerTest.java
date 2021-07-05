package com.avijitmondal.together.auth.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UnsecuredControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void unsecuredResourceTest() throws Exception {
        mockMvc.perform(get("/unsecured"))
                .andExpect(status().isOk())
                .andExpect(content().string("This resource is not secured"));
    }
}
