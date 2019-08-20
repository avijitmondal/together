package com.avijit.together.registration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TogetherRegistrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherRegistrationApplication.class, args);
	}

}
