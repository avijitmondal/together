/*****************************************************************************
 * FILE NAME   : TogetherException.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 7, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.exception;

/**
 * @author avijit
 *
 */
public class TogetherException extends Throwable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private final ErrorCode errorCode;
	/**
	 * 
	 */
	private final String errorDetails;

	/**
	 * @param errorCode
	 * @param errorDetails
	 */
	public TogetherException(ErrorCode errorCode, String errorDetails) {
		super(errorDetails);
		this.errorCode = errorCode;
		this.errorDetails = errorDetails;

	}

	/**
	 * @return the errorCode
	 */
	public ErrorCode getErrorCode() {
		return errorCode;
	}

	/**
	 * @return the errorDetails
	 */
	public String getErrorDetails() {
		return errorDetails;
	}

}
