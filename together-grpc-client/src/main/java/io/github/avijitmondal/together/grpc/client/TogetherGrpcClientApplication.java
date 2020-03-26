package io.github.avijitmondal.together.grpc.client;

import io.github.avijitmondal.together.grpc.client.service.HelloWorldClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TogetherGrpcClientApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TogetherGrpcClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new HelloWorldClient("localhost", 50051).start();
	}
}
