package com.avijit.together.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TogetherUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherUserApplication.class, args);
	}

}
