/*****************************************************************************
 * FILE NAME   : IErrorDetails.java
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
public interface IErrorDetails {
	String USER_ID_NOT_FOUND = "User with ID %s not found";
	String MESSAGE_ID_NOT_FOUND = "Message with ID %s not found";
	String AUTHENTICATION_ID_NOT_FOUND = "Authentication with ID %s not found";
	String CONVERSATION_ID_NOT_FOUND = "Conversation with ID %s not found";
	String USER_EMAIL_NOT_FOUND = "User with email ID %s not found";
	String USER_USERNAME_NOT_FOUND = "User with username %s not found";
	String CONVERSATIONS_USER_ID_NOT_FOUND = "Conversation with user ID %s not found";
	String FILE_NOT_FOUND = "File with name %s not found";
	
	String UNABLE_TO_ADD_USER = "Unable to add new user";
	String UNABLE_TO_ADD_CONVERSATION = "Unable to add new conversation";
	String UNABLE_TO_ADD_AUTHENTICATION = "Unable to add new authentication";
	String UNABLE_TO_ADD_MESSAGE = "Unable to add new message";
	String UNABLE_TO_ADD_PARTICIPANT = "Unable to add new participant";
	String UNABLE_TO_ADD_FILE = "Unable to add new file";
	
	String INVALID_USER_ID = "Invalid user ID: %s";
	String INVALID_MESSAGE_ID = "Invalid message ID: %s";
	String INVALID_AUTHENTICATION_ID = "Invalid authentication ID: %s";
	String INVALID_CONVERSATION_ID = "Invalid conversation ID: %s";
	String INVALID_PARTICIPANT_ID = "Invalid participant ID: %s";
	String INVALID_FILE_ATTACHED = "Invalid file attached: %s";
	
	String ENTER_VALID_USER_ID = "Please enter a valid user ID";
	String ENTER_VALID_USER_EMAIL = "Please enter a valid email ID";
	String ENTER_VALID_CONVERSATION_ID = "Please enter a valid conversation ID";
	String ENTER_VALID_PARTICIPANT_ID = "Please enter a valid participant ID";
	String ENTER_VALID_MESSAGE_ID = "Please enter a valid message ID";
	String ENTER_VALID_AUTHENTICATION_ID = "Please enter a valid authentication ID";
	String ENTER_VALID_FILE_NAME = "Please enter a valid file name";
	String ENTER_VALID_FILE = "Please attach a valid file";
	String ENTER_VALID_DATA = "Please enter valid data(s)";

	String TRY_SOMETIME_LATER = "Please try after some time";
}
