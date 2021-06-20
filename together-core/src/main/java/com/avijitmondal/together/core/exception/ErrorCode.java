/*****************************************************************************
 * FILE NAME   : ErrorCode.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 3, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.exception;

/**
 * @author avijit
 *
 */
public enum ErrorCode {
	/**
	* 
	*/
	IO_EXCEPTION("1001"),
	/**
	* 
	*/
	WRONG_PARAMETER("1002"),

	/**
	* 
	*/
	INVALID_USERNAME("1003"),
	/**
	* 
	*/
	INVALID_PASSWORD("1004"),
	/**
	* 
	*/
	INVALID_EMAIL("1005"),
	/**
	* 
	*/
	INVALID_CONVERSATION_ID("1006"),
	/**
	* 
	*/
	INVALID_PARTICIPANT_ID("1007"),
	/**
	* 
	*/
	INVALID_USER_ID("1008"),
	/**
	* 
	*/
	INVALID_AUTHENTICATION_ID("1009"),
	/**
	* 
	*/
	INVALID_MESSAGE_ID("1010"),

	/**
	 * 
	 */
	FILE_NOT_FOUND("1011"),
	/**
	* 
	*/
	USER_NOT_FOUND("1012"),
	/**
	* 
	*/
	CONVERSATION_NOT_FOUND("1013"),
	/**
	* 
	*/
	MESSAGE_NOT_FOUND("1014"),
	/**
	* 
	*/
	AUTHENTICATION_NOT_FOUND("1015"),
	
	/**
	* 
	*/
	USER_NOT_ADDED("1016"),
	/**
	* 
	*/
	CONVERSATION_NOT_ADDED("1017"),
	/**
	* 
	*/
	PARTICIPANT_NOT_ADDED("1018"),
	/**
	* 
	*/
	MESSAGE_NOT_ADDED("1019"),
	/**
	* 
	*/
	AUTHENTICATION_NOT_ADDED("1020"),
	/**
	 * 
	 */
	FILE_NOT_ADDED("1021"),
	/**
	 *
	 */
	DIRECTORY_NOT_CREATED("1023"),
	/**
	 *
	 */
	INVALID_FILE_NAME("1024");

	/**
	 * 
	 */
	private final String code;

	/**
	 * @param code
	 */
	private ErrorCode(String code) {
		this.code = code;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
