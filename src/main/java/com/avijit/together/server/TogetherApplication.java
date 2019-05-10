package com.avijit.together.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * @author avijit
 *
 */
@SpringBootApplication
@EnableConfigurationProperties
public class TogetherApplication {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(TogetherApplication.class, args);
	}
}
