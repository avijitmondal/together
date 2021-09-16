package com.avijitmondal.together.conversation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConversationApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConversationApplication.class, args);
	}

}
