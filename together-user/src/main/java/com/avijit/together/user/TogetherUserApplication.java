package com.avijit.together.user;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.xmlpull.v1.XmlPullParserException;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
@EnableEurekaClient
@EnableSwagger2
public class TogetherUserApplication {

	public static void main(String[] args) {
		SpringApplication.run(TogetherUserApplication.class, args);
	}

	@Bean
	public Docket api() {
		MavenXpp3Reader reader = new MavenXpp3Reader();
		Model model = null;
		try {
			model = reader.read(new FileReader("pom.xml"));
		} catch (Exception exception) {

		}

		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.avijit.together.user.controller"))
				.paths(PathSelectors.any())
				.build().apiInfo(new ApiInfo("Account Service Api Documentation", "Documentation automatically generated", model.getParent().getVersion(), null, new Contact("Avijit Mondal", "avijitmondal.wordpress.com", "avijitmondal38@gmail.com"), null, null));
	}
}
