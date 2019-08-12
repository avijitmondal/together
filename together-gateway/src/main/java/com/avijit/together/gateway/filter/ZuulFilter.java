package com.avijit.together.gateway.filter;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class ZuulFilter {
    @Bean
    public PreFilter preFilter() {
        return new PreFilter();
    }

    @Bean
    public PostFilter postFilter() {
        return new PostFilter();
    }

    @Bean
    public ErrorFilter errorFilter() {
        return new ErrorFilter();
    }

    @Bean
    public RouteFilter routeFilter() {
        return new RouteFilter();
    }
}
