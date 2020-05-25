package com.avijit.together.conversation.config;

import com.avijit.together.core.util.EnvironmentValuesReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VisibilityConfiguration {
    @Bean
    public EnvironmentValuesReader publicEnvironmentValuesReader() {
        return EnvironmentValuesReader.getInstance();
    }

}
