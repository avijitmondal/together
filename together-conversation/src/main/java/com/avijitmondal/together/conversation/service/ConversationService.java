/*****************************************************************************
 * FILE NAME   : ConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.conversation.service;

import com.avijitmondal.together.core.data.Constants;
import com.avijitmondal.together.core.dto.Conversation;
import com.avijitmondal.together.core.dto.ResponseDTO;
import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.core.util.EnvironmentValuesReader;
import com.avijitmondal.together.core.util.parser.GsonParser;
import com.avijitmondal.together.core.ws.HttpMethod;
import com.avijitmondal.together.core.ws.RestService;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author avijit
 *
 */
@Service("conversationService")
public class ConversationService implements IConversationService {

	@Autowired
	private EnvironmentValuesReader environmentValuesReader;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IConversationService#findAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public ResponseDTO<List<Conversation>> findAll(Pageable pageable) {
		RestService restService = new RestService(HttpMethod.GET, false, environmentValuesReader.getTogetherDatabaseUrl() + Constants.API_CONVERSATIONS);
		try{
			restService.execute();
			if (restService.isSuccessResponse(HttpStatus.SC_OK)) {
				String responseAsString = restService.getSuccessResponse();

				return  (ResponseDTO<List<Conversation>>) GsonParser.fromString(responseAsString, new TypeToken<ResponseDTO<List<Conversation>>>() {
				}.getType());
			}
		} catch (Exception exception) {
			return new ResponseDTO<>();
		}
		return new ResponseDTO<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IConversationService#findByUserId(org.
	 * springframework.data.domain.Pageable, java.lang.String)
	 */
	@Override
	public ResponseDTO<List<Conversation>> findByUserId(Pageable pageable, String userId) throws TogetherException {
		RestService restService = new RestService(HttpMethod.GET, false, environmentValuesReader.getTogetherDatabaseUrl() + Constants.API_CONVERSATIONS + "/" + userId);
		try{
			restService.execute();
			if (restService.isSuccessResponse(HttpStatus.SC_OK)) {
				String responseAsString = restService.getSuccessResponse();

				return  (ResponseDTO<List<Conversation>>) GsonParser.fromString(responseAsString, new TypeToken<ResponseDTO<List<Conversation>>>() {
				}.getType());
			}
		} catch (Exception exception) {
			return new ResponseDTO<>();
		}
		return new ResponseDTO<>();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IConversationService#findById(java.
	 * lang.String)
	 */
	@Override
	public Optional<Conversation> findById(String conversationId) throws TogetherException {
		try {
			RestService restService = new RestService(HttpMethod.GET, false, environmentValuesReader.getTogetherDatabaseUrl() + Constants.API_CONVERSATIONS + "/" + conversationId);

			restService.execute();

			if (restService.isSuccessResponse(HttpStatus.SC_OK)) {
				String responseAsString = restService.getSuccessResponse();
				Conversation response = GsonParser.fromString(responseAsString, Conversation.class);

				return Optional.of(response);
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IConversationService#save(com.avijit.
	 * together.server.model.Conversation)
	 */
	@Override
	public Conversation save(Conversation conversation) throws TogetherException {
		try {
			RestService restService = new RestService(HttpMethod.POST, false, environmentValuesReader.getTogetherDatabaseUrl() + Constants.API_CONVERSATIONS);

			restService.execute();
			if (restService.isSuccessResponse(HttpStatus.SC_CREATED)) {
				String responseAsString = restService.getSuccessResponse();
				return GsonParser.fromString(responseAsString, Conversation.class);
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
	 * @see
	 * com.avijit.together.database.service.IConversationService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String conversationId) throws TogetherException {
		try {
			RestService restService = new RestService(HttpMethod.DELETE, false, environmentValuesReader.getTogetherDatabaseUrl() + Constants.API_CONVERSATIONS + "/" + conversationId);

			restService.execute();

			if (restService.isSuccessResponse(HttpStatus.SC_NO_CONTENT)) {
				return true;
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IConversationService#isExists(java.
	 * lang.String)
	 */
	@Override
	public boolean isExists(String conversationId) throws TogetherException {
		try {
			// TODO: IMPLEMENT ME
//			return iUserRepository.existsById(UUID.fromString(userId));
			return false;
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID,
					String.format(IErrorDetails.INVALID_CONVERSATION_ID, conversationId));
		}

	}

}
