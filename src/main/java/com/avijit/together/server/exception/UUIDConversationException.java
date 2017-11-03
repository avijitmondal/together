/*****************************************************************************
 * FILE NAME   : Exception.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.exception;

/**
 * @author avijit
 *
 */
public class UUIDConversationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public UUIDConversationException() {
		super();
	}

	/**
	 * @param message
	 */
	public UUIDConversationException(String message) {
		super(message);
	}
}
