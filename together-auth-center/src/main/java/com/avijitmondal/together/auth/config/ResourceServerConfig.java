package com.avijitmondal.together.auth.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private DefaultTokenServices tokenServices;
    @Autowired
    public TokenStore tokenStore;

    // TODO: move to config file
    private final String resourceId = "auth-center-resource";

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // FIXME: Only allow required resources restrict otherwise
                .antMatchers("/secured/**").authenticated()
                .anyRequest().permitAll();

        http.headers().frameOptions().sameOrigin();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources
                .resourceId(resourceId)
                .tokenServices(tokenServices)
                .tokenStore(tokenStore);
    }
}
