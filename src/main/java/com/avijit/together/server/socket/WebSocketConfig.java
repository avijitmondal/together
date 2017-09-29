package com.avijit.together.server.socket;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

import com.avijit.together.server.util.IURIConstant;

/**
 * @author avijit
 *
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.socket.config.annotation.
	 * AbstractWebSocketMessageBrokerConfigurer#configureMessageBroker(org.
	 * springframework.messaging.simp.config.MessageBrokerRegistry)
	 */
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker(IURIConstant.URI_BROKER_TOPIC, IURIConstant.URI_BROKER_QUEUE);
		config.setApplicationDestinationPrefixes(IURIConstant.URI_DESTINATION_PREFIX);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.socket.config.annotation.
	 * WebSocketMessageBrokerConfigurer#registerStompEndpoints(org.
	 * springframework.web.socket.config.annotation.StompEndpointRegistry)
	 */
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint(IURIConstant.URI_BROKER_ENDPOINT).addInterceptors(new HttpSessionIdHandshakeInterceptor())
				.setAllowedOrigins("*").withSockJS();
	}

}
