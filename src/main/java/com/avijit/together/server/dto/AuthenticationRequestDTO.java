package com.avijit.together.server.dto;

import java.io.Serializable;

/**
 * @author avijit
 *
 */
public class AuthenticationRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private String username;
	/**
	 * 
	 */
	private String password;

	/**
	 * 
	 */
	public AuthenticationRequestDTO() {
		super();
	}

	/**
	 * @param username
	 * @param password
	 */
	public AuthenticationRequestDTO(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	/**
	 * @return
	 */
	public String getUsername() {
		return this.username;
	}

	/**
	 * @param username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
}
