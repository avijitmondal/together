package com.avijitmondal.together.sync;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SyncApplicationTest {

    @Test
    void main() {
        SyncApplication.main(new String[]{});
        Assertions.assertTrue(true);
    }
}
