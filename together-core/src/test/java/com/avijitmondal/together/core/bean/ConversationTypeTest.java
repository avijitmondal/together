package com.avijitmondal.together.core.bean;

import junit.framework.TestCase;

public class ConversationTypeTest extends TestCase {

    public void testValues() {
        assertNotNull(ConversationType.SINGLE);
        assertNotNull(ConversationType.GROUP);
    }
}