package com.avijitmondal.together.core.dto;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AuthenticationResponseDTOTest extends TestCase {

    private AuthenticationResponseDTO authenticationResponseDTO;
    public AuthenticationResponseDTOTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(AuthenticationResponseDTOTest.class);
    }

    public void testArgConstructor() {
        assertNull(authenticationResponseDTO);
    }
}