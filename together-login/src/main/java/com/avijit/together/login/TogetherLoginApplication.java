package com.avijit.together.login;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TogetherLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherLoginApplication.class, args);
	}

}
