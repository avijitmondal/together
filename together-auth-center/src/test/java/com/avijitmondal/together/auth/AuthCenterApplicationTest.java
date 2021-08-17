package com.avijitmondal.together.auth;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthCenterApplicationTest {

    @Test
    void main() {
        AuthCenterApplication.main(new String[]{});
    }
}
