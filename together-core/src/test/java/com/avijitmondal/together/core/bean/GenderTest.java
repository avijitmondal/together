package com.avijitmondal.together.core.bean;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class GenderTest {

    @Test
    void testValues() {
        assertNotNull(Gender.MALE);
        assertNotNull(Gender.FEMALE);
        assertNotNull(Gender.OTHERS);
    }
}