package com.avijitmondal.together.discovery;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DiscoveryApplicationTests {

	@Test
	void main() {
		DiscoveryApplication.main(new String[]{});
		Assertions.assertTrue(true);
	}
}
