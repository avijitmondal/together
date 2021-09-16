package com.avijitmondal.together.gateway;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class GatewayApplicationTests {

	@Test
	void main() {
		GatewayApplication.main(new String[]{});
		Assertions.assertTrue(true);
	}

}
