package com.avijit.together.server.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.avijit.together.server.util.URIConstant;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(URIConstant.URI_BROKER_TOPIC, URIConstant.URI_BROKER_QUEUE);
		config.setApplicationDestinationPrefixes(URIConstant.URI_DESTINATION_PREFIX);
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(URIConstant.URI_BROKER_ENDPOINT).addInterceptors(new HttpSessionIdHandshakeInterceptor())
				.setAllowedOrigins("*").withSockJS();
	}

}
