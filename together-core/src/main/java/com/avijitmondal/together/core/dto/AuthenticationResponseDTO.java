package com.avijitmondal.together.core.dto;

import java.io.Serializable;


/**
 * @author avijit
 *
 */
public class AuthenticationResponseDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private final User user;
	/**
	 * 
	 */
	private final String token;
	/**
	 * 
	 */
	private String expiration = "86400";

	/**
	 * @param token
	 * @param user
	 */
	public AuthenticationResponseDTO(String token, User user) {
		this.token = token;
		this.user = user;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @return
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @return the expiration
	 */
	public String getExpiration() {
		return expiration;
	}
}
