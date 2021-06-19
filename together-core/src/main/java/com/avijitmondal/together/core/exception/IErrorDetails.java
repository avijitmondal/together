/*****************************************************************************
 * FILE NAME   : IErrorDetails.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 7, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.core.exception;

/**
 * @author avijit
 *
 */
public abstract class IErrorDetails {
	public static final String USER_ID_NOT_FOUND = "User with ID %s not found";
	public static final String MESSAGE_ID_NOT_FOUND = "Message with ID %s not found";
	public static final String AUTHENTICATION_ID_NOT_FOUND = "Authentication with ID %s not found";
	public static final String CONVERSATION_ID_NOT_FOUND = "Conversation with ID %s not found";
	public static final String USER_EMAIL_NOT_FOUND = "User with email ID %s not found";
	public static final String USER_USERNAME_NOT_FOUND = "User with username %s not found";
	public static final String CONVERSATIONS_USER_ID_NOT_FOUND = "Conversation with user ID %s not found";
	public static final String FILE_NOT_FOUND = "File with name %s not found";
	
	public static final String UNABLE_TO_ADD_USER = "Unable to add new user";
	public static final String UNABLE_TO_ADD_CONVERSATION = "Unable to add new conversation";
	public static final String UNABLE_TO_ADD_AUTHENTICATION = "Unable to add new authentication";
	public static final String UNABLE_TO_ADD_MESSAGE = "Unable to add new message";
	public static final String UNABLE_TO_ADD_PARTICIPANT = "Unable to add new participant";
	public static final String UNABLE_TO_ADD_FILE = "Unable to add new file";
	
	public static final String INVALID_USER_ID = "Invalid user ID: %s";
	public static final String INVALID_MESSAGE_ID = "Invalid message ID: %s";
	public static final String INVALID_AUTHENTICATION_ID = "Invalid authentication ID: %s";
	public static final String INVALID_CONVERSATION_ID = "Invalid conversation ID: %s";
	public static final String INVALID_PARTICIPANT_ID = "Invalid participant ID: %s";
	public static final String INVALID_FILE_ATTACHED = "Invalid file attached: %s";
	
	public static final String ENTER_VALID_USER_ID = "Please enter a valid user ID";
	public static final String ENTER_VALID_USER_EMAIL = "Please enter a valid email ID";
	public static final String ENTER_VALID_CONVERSATION_ID = "Please enter a valid conversation ID";
	public static final String ENTER_VALID_PARTICIPANT_ID = "Please enter a valid participant ID";
	public static final String ENTER_VALID_MESSAGE_ID = "Please enter a valid message ID";
	public static final String ENTER_VALID_AUTHENTICATION_ID = "Please enter a valid authentication ID";
	public static final String ENTER_VALID_FILE_NAME = "Please enter a valid file name";
	public static final String ENTER_VALID_FILE = "Please attach a valid file";
	public static final String ENTER_VALID_DATA = "Please enter valid data(s)";

	public static final String TRY_SOMETIME_LATER = "Please try after some time";

	public static final String UNABLE_TO_CREATE_DIRECTORY = "Unable to create directory";
	public static final String FILE_NOT_ADDED = "File with name %s not added";
	public static final String INVALID_FILE_NAME = "Invalid filename %s";
}
