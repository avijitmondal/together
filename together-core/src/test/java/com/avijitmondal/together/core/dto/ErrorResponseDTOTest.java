package com.avijitmondal.together.core.dto;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ErrorResponseDTOTest extends TestCase {
    private ErrorResponseDTO errorResponseDTO;
    public ErrorResponseDTOTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(ErrorResponseDTO.class);
    }

    public void testArgConstructor() {
        assertNull(errorResponseDTO);
    }
}