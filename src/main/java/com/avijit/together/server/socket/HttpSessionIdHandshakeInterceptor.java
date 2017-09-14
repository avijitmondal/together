/**
 * 
 */
package com.avijit.together.server.socket;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

/**
 * @author avijit
 *
 */
public class HttpSessionIdHandshakeInterceptor implements HandshakeInterceptor {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.socket.server.HandshakeInterceptor#afterHandshake
	 * (org.springframework.http.server.ServerHttpRequest,
	 * org.springframework.http.server.ServerHttpResponse,
	 * org.springframework.web.socket.WebSocketHandler, java.lang.Exception)
	 */
	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Exception ex) {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.socket.server.HandshakeInterceptor#
	 * beforeHandshake(org.springframework.http.server.ServerHttpRequest,
	 * org.springframework.http.server.ServerHttpResponse,
	 * org.springframework.web.socket.WebSocketHandler, java.util.Map)
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
			Map<String, Object> attributes) throws Exception {
		if (request instanceof ServletServerHttpRequest) {
			ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
			HttpSession session = servletRequest.getServletRequest().getSession();
			attributes.put("sessionId", session.getId());
		}
		return true;
	}

}
