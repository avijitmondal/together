package com.avijitmondal.together.ftp;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FTPApplicationTests {

	@Test
	void main() {
		FTPApplication.main(new String[]{});
	}

}
