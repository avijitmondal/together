/**
 * 
 */
package com.avijit.together.server.socket;

import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

/**
 * @author avijit
 *
 */
@Component
public class SubscribeEventListener implements ApplicationListener<SessionSubscribeEvent> {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.context.ApplicationListener#onApplicationEvent(org.
	 * springframework.context.ApplicationEvent)
	 */
	@Override
	public void onApplicationEvent(SessionSubscribeEvent sessionSubscribeEvent) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(sessionSubscribeEvent.getMessage());
		// System.out.println(headerAccessor.getSessionAttributes().get("sessionId").toString());
		System.out.println(headerAccessor);
	}
}