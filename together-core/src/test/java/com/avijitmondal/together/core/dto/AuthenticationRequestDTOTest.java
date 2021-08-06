package com.avijitmondal.together.core.dto;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AuthenticationRequestDTOTest extends TestCase {

    private AuthenticationRequestDTO authenticationRequestDTO;
    public AuthenticationRequestDTOTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AuthenticationRequestDTOTest.class);
    }

    public void testArgConstructor() {
        assertNull(authenticationRequestDTO);
        authenticationRequestDTO = new AuthenticationRequestDTO("user", "password");
        assertNotNull(authenticationRequestDTO.getUsername());
        assertNotNull(authenticationRequestDTO.getPassword());
        authenticationRequestDTO = null;
    }

    public void testUsername() {
        assertNull(authenticationRequestDTO);
        authenticationRequestDTO = new AuthenticationRequestDTO();
        assertNull(authenticationRequestDTO.getUsername());
        authenticationRequestDTO.setUsername("user");
        assertNotNull(authenticationRequestDTO.getUsername());
        authenticationRequestDTO = null;
    }

    public void testPassword() {
        assertNull(authenticationRequestDTO);
        authenticationRequestDTO = new AuthenticationRequestDTO();
        assertNull(authenticationRequestDTO.getPassword());
        authenticationRequestDTO.setPassword("password");
        assertNotNull(authenticationRequestDTO.getPassword());
        authenticationRequestDTO = null;
    }
}