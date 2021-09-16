package com.avijitmondal.together.conversation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
class ConversationApplicationTests {

	@Test
	void main() {
		ConversationApplication.main(new String[]{});
		Assertions.assertTrue(true);
	}

}
