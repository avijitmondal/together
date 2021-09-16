package com.avijitmondal.together.conversation.socket;

import com.avijitmondal.together.core.data.Constants;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;

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
		config.enableSimpleBroker(Constants.URI_BROKER_TOPIC, Constants.URI_BROKER_QUEUE);
		config.setApplicationDestinationPrefixes(Constants.URI_DESTINATION_PREFIX);
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
		registry.addEndpoint(Constants.URI_BROKER_ENDPOINT).addInterceptors(new HttpSessionIdHandshakeInterceptor())
				.setAllowedOrigins("*").withSockJS();
	}

}
