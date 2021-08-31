package com.avijitmondal.together.core.dto;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class AuthenticationRequestDTOTest {

    private AuthenticationRequestDTO authenticationRequestDTO;

    @Test
    public void testArgConstructor() {
        assertNull(authenticationRequestDTO);
        authenticationRequestDTO = new AuthenticationRequestDTO("user", "password");
        assertNotNull(authenticationRequestDTO.getUsername());
        assertNotNull(authenticationRequestDTO.getPassword());
        authenticationRequestDTO = null;
    }

    @Test
    public void testUsername() {
        assertNull(authenticationRequestDTO);
        authenticationRequestDTO = new AuthenticationRequestDTO();
        assertNull(authenticationRequestDTO.getUsername());
        authenticationRequestDTO.setUsername("user");
        assertNotNull(authenticationRequestDTO.getUsername());
        authenticationRequestDTO = null;
    }

    @Test
    public void testPassword() {
        assertNull(authenticationRequestDTO);
        authenticationRequestDTO = new AuthenticationRequestDTO();
        assertNull(authenticationRequestDTO.getPassword());
        authenticationRequestDTO.setPassword("password");
        assertNotNull(authenticationRequestDTO.getPassword());
        authenticationRequestDTO = null;
    }
}