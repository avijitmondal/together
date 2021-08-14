package com.avijitmondal.together.alive.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.client.LinkDiscoverer;
import org.springframework.hateoas.client.LinkDiscoverers;
import org.springframework.hateoas.mediatype.collectionjson.CollectionJsonLinkDiscoverer;
import org.springframework.plugin.core.SimplePluginRegistry;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(generateAPIInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.avijitmondal.together.alive.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    @Bean
    public LinkDiscoverers discoverers() {
        List<LinkDiscoverer> plugins = new ArrayList<>();
        plugins.add(new CollectionJsonLinkDiscoverer());
        return new LinkDiscoverers(SimplePluginRegistry.create(plugins));

    }

    //Api information
    private ApiInfo generateAPIInfo() {
        return new ApiInfo("together-alive Swagger", "Swagger for together-alive", "1.0",
                "https://github.com/avijitmondal/together/", getContacts(), "MIT", "https://github.com/avijitmondal/together/blob/master/LICENSE", new ArrayList<>());
    }

    // Developer Contacts
    private Contact getContacts() {
        return new Contact("Avijit", "http://avijitmondal.github.io/", "avijitmondal38@gmail.com");
    }
}
