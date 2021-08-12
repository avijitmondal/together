package com.avijitmondal.together.user;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserApplicationTests {

    @Test
    void main() {
		UserApplication.main(new String[]{});
        Assertions.assertTrue(true);
    }
}
