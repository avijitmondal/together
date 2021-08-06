package com.avijitmondal.together.core.bean;

import junit.framework.TestCase;

public class GenderTest extends TestCase {

    public void testValues() {
        assertNotNull(Gender.MALE);
        assertNotNull(Gender.FEMALE);
        assertNotNull(Gender.OTHERS);
    }
}