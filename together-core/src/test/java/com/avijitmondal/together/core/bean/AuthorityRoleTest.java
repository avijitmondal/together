package com.avijitmondal.together.core.bean;

import junit.framework.TestCase;

public class AuthorityRoleTest extends TestCase {

    public void testValues() {
        assertNotNull(AuthorityRole.ROLE_ADMIN);
        assertNotNull(AuthorityRole.ROLE_USER);
    }
}