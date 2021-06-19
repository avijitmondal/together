package com.avijitmondal.together.user.config;

import com.avijitmondal.together.core.util.EnvironmentValuesReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VisibilityConfiguration {
    @Bean
    public EnvironmentValuesReader publicEnvironmentValuesReader() {
        return EnvironmentValuesReader.getInstance();
    }

}
