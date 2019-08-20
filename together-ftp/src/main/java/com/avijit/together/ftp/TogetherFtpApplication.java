package com.avijit.together.ftp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TogetherFtpApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherFtpApplication.class, args);
	}

}
