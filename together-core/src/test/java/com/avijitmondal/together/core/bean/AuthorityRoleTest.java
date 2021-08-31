package com.avijitmondal.together.core.bean;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class AuthorityRoleTest {

    @Test
    void testValues() {
        assertNotNull(AuthorityRole.ROLE_ADMIN);
        assertNotNull(AuthorityRole.ROLE_USER);
    }
}