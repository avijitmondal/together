/*****************************************************************************
 * FILE NAME   : MessageService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.conversation.service;

import java.util.Optional;
import java.util.UUID;

import com.avijit.together.core.data.Constants;
import com.avijit.together.core.dto.Message;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.core.util.parser.GsonParser;
import com.avijit.together.core.ws.HttpMethod;
import com.avijit.together.core.ws.RestService;
import org.apache.http.HttpStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author avijit
 *
 */
@Service("messageService")
public class MessageService implements IMessageService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IMessageService#findById(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Optional<Message> findById(String conversationId, String messageId) throws TogetherException {
		try {
			var restService = new RestService(HttpMethod.GET, false, Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_CONVERSATIONS + "/" + conversationId + "/messages/" + messageId);
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
			var restService = new RestService(HttpMethod.POST, false, Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_CONVERSATIONS);

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
