package com.avijitmondal.together.core.bean;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class MessageTypeTest {

    @Test
    void testValues() {
        assertNotNull(MessageType.AUDIO);
        assertNotNull(MessageType.VIDEO);
        assertNotNull(MessageType.TEXT);
        assertNotNull(MessageType.DOCUMENT);
        assertNotNull(MessageType.IMAGE);
    }
}