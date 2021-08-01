package com.avijitmondal.together.gateway;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GatewayApplicationTests {

	@Test
	public void main() {
		GatewayApplication.main(new String[]{});
	}

}
