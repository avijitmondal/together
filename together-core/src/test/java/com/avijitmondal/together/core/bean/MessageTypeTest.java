package com.avijitmondal.together.core.bean;

import junit.framework.TestCase;

public class MessageTypeTest extends TestCase {

    public void testValues() {
        assertNotNull(MessageType.AUDIO);
        assertNotNull(MessageType.VIDEO);
        assertNotNull(MessageType.TEXT);
        assertNotNull(MessageType.DOCUMENT);
        assertNotNull(MessageType.IMAGE);
    }
}