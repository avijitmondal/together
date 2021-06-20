/*****************************************************************************
 * FILE NAME   : TogetherException.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 7, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.exception;

import com.avijitmondal.together.core.dto.ErrorResponseDTO;

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

	public TogetherException(ErrorResponseDTO errorResponse) {
		this.errorCode = ErrorCode.valueOf(errorResponse.getErrorCode());
		this.errorDetails = errorResponse.getErrorDetails();
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
