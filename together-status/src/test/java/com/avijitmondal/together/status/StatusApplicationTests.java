package com.avijitmondal.together.status;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class StatusApplicationTests {

	@Test
	void contextLoads() {
		assert true;
	}

    @Test
    void main() {
		StatusApplication.main(new String[]{});
    }
}
