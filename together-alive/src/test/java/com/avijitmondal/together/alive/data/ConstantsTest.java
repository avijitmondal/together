package com.avijitmondal.together.alive.data;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ConstantsTest {

    @Test
    void getInstance() {
        assertThrows(InvocationTargetException.class, () -> {
            Constructor[] constructors = Constants.class.getDeclaredConstructors();
            for (Constructor constructor : constructors) {
                constructor.setAccessible(true);
                constructor.newInstance();
            }
        });
    }

    @Test
    void sessionIdTest() {
        assertEquals(1000, Constants.TIME_INTERVAL);
        assertEquals(5, Constants.TIME_LIFE_FRAMES);
        assertEquals(3, Constants.TIME_LOSS_COMMUNICATION_FRAME);
        assertEquals(1024, Constants.UDP_VALID_PACKET_SIZE);
    }
}