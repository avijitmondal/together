package com.avijitmondal.together.config;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConfigApplicationTests {

	@Test
	void main() {
		ConfigApplication.main(new String[]{});
		Assertions.assertTrue(true);
	}
}
