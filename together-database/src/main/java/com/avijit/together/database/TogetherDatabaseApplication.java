package com.avijit.together.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class TogetherDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TogetherDatabaseApplication.class, args);
    }

}
