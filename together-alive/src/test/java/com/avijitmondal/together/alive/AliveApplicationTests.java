package com.avijitmondal.together.alive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AliveApplicationTests {


    @Test
    void main() {
        AliveApplication.main(new String[]{});
        Assertions.assertTrue(true);
    }
}
