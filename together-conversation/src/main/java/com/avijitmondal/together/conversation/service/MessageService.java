/*****************************************************************************
 * FILE NAME   : MessageService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.conversation.service;

import java.util.Optional;

import com.avijitmondal.together.core.data.Constants;
import com.avijitmondal.together.core.dto.Message;
import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.core.util.EnvironmentValuesReader;
import com.avijitmondal.together.core.util.parser.GsonParser;
import com.avijitmondal.together.core.ws.HttpMethod;
import com.avijitmondal.together.core.ws.RestService;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author avijit
 *
 */
@Service("messageService")
public class MessageService implements IMessageService {

	@Autowired
	private EnvironmentValuesReader environmentValuesReader;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IMessageService#findById(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Optional<Message> findById(String conversationId, String messageId) throws TogetherException {
		try {
			var restService = new RestService(HttpMethod.GET, false, environmentValuesReader.getTogetherDatabaseUrl() + Constants.API_CONVERSATIONS + "/" + conversationId + "/messages/" + messageId);
			restService.execute();

			if (restService.isSuccessResponse(HttpStatus.SC_OK)) {
				var responseAsString = restService.getSuccessResponse();
				var response = GsonParser.fromString(responseAsString, Message.class);

				return Optional.of(response);
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}

		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_MESSAGE_ID, IErrorDetails.INVALID_MESSAGE_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IMessageService#save(com.avijit.
	 * together.server.model.Message)
	 */
	@Override
	public Message save(Message message) throws TogetherException {
		try {
			var restService = new RestService(HttpMethod.POST, false, environmentValuesReader.getTogetherDatabaseUrl() + Constants.API_CONVERSATIONS);

			restService.execute();
			if (restService.isSuccessResponse(HttpStatus.SC_CREATED)) {
				var responseAsString = restService.getSuccessResponse();
				return GsonParser.fromString(responseAsString, Message.class);
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.CONVERSATION_NOT_ADDED, IErrorDetails.UNABLE_TO_ADD_CONVERSATION);
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IMessageService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String messageId) throws TogetherException {
		try {
			if (isExists(messageId)) {
//				iMessageRepository.delete(UUID.fromString(messageId));
				return true;
			} else {
				return false;
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_MESSAGE_ID, IErrorDetails.INVALID_MESSAGE_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IMessageService#findByConversationId(
	 * org.springframework.data.domain.Pageable, java.lang.String)
	 */
	@Override
	public Page<Message> findByConversationId(Pageable pageable, String conversationId) throws TogetherException {
		try {
//			return iMessageRepository.findByConversationId(pageable, UUID.fromString(conversationId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IMessageService#isExists(java.lang.String)
	 */
	@Override
	public boolean isExists(String messageId) throws TogetherException {
		try {
//			return iMessageRepository.exists(UUID.fromString(messageId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_MESSAGE_ID,
					String.format(IErrorDetails.INVALID_MESSAGE_ID, messageId));
		}
		return false;
	}

}
