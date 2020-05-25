package com.avijitmondal.together.alive;

import com.avijitmondal.together.alive.service.HeartbeatHandler;
import com.avijitmondal.together.alive.service.HeartbeatManager;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class TogetherAliveApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(TogetherAliveApplication.class, args);
    }

    @Override
    public void run(String... args) {
        HeartbeatManager heartbeatManager = new HeartbeatManager(new HeartbeatHandler());
        heartbeatManager.addHost("together", 9092);
    }
}
