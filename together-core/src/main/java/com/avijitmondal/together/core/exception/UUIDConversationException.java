/*****************************************************************************
 * FILE NAME   : Exception.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.exception;

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
