package com.avijitmondal.together.ftp;

import com.avijitmondal.together.ftp.properties.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableConfigurationProperties({
		FileStorageProperties.class
})
public class TogetherFTPApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherFTPApplication.class, args);
	}

}
