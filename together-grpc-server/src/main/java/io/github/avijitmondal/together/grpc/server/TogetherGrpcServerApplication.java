package io.github.avijitmondal.together.grpc.server;

import io.github.avijitmondal.together.grpc.server.service.HelloWorldServer;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TogetherGrpcServerApplication implements CommandLineRunner {


	public static void main(String[] args) {
		SpringApplication.run(TogetherGrpcServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		new HelloWorldServer().start();
	}
}
