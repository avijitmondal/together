package com.avijitmondal.together.core.bean;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class ConversationTypeTest{

    @Test
    void testValues() {
        assertNotNull(ConversationType.SINGLE);
        assertNotNull(ConversationType.GROUP);
    }
}