package com.avijitmondal.together.core.dto;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ConversationTest extends TestCase {
    private Conversation conversation;
    public ConversationTest(String testName) {
        super(testName);
    }

    public static Test suite() {
        return new TestSuite(Conversation.class);
    }

    public void testArgConstructor() {
        assertNull(conversation);
    }
}