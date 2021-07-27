package com.avijitmondal.together.user;

import com.avijitmondal.together.user.controller.UserController;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserApplicationTests {

	@Autowired
	private UserController userController;

	@Test
	public void contextLoads() {
		Assertions.assertNotNull(userController);
	}

}
