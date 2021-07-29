package com.avijitmondal.together.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApplicationTests {

    @Test
    public void main() {
		UserApplication.main(new String[]{});
    }
}
