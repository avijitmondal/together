package com.avijitmondal.together.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TogetherAuthCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(TogetherAuthCenterApplication.class, args);
    }
}
