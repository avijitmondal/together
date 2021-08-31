package com.avijitmondal.together.core.data.cache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CentralCacheTest {


    @Test
    void testGetInstance() {
        assertNotNull(CentralCache.getInstance());
    }
}