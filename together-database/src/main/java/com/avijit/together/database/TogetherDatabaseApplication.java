package com.avijit.together.database;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"together-database"})
@EnableJpaRepositories({"together-core"})
@SpringBootApplication
public class TogetherDatabaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(TogetherDatabaseApplication.class, args);
    }

}
