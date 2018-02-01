/*****************************************************************************
 * FILE NAME   : ConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijit.together.server.exception.ErrorCode;
import com.avijit.together.server.exception.IErrorDetails;
import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.Conversation;
import com.avijit.together.server.repository.IConversationRepository;

/**
 * @author avijit
 *
 */
@Service("conversationService")
public class ConversationService implements IConversationService {

	/**
	 * 
	 */
	@Autowired
	private IConversationRepository iConversationRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IConversationService#findAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Conversation> findAll(Pageable pageable) {
		return iConversationRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IConversationService#findByUserId(org.
	 * springframework.data.domain.Pageable, java.lang.String)
	 */
	@Override
	public Page<Conversation> findByUserId(Pageable pageable, String userId) throws TogetherException {
		try {
			return iConversationRepository.findByUserId(pageable, UUID.fromString(userId));
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_USER_ID,
					String.format(IErrorDetails.CONVERSATIONS_USER_ID_NOT_FOUND, userId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IConversationService#findById(java.
	 * lang.String)
	 */
	@Override
	public Conversation findById(String conversationId) throws TogetherException {
		try {
			return iConversationRepository.findOne(UUID.fromString(conversationId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID,
					String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IConversationService#save(com.avijit.
	 * together.server.model.Conversation)
	 */
	@Override
	public Conversation save(Conversation conversation) throws TogetherException {
		try {
			conversation.setId(UUID.randomUUID());
			conversation.setCreatedAt(LocalDateTime.now());
			conversation.setUpdatedAt(LocalDateTime.now());
			conversation.getParticipants().forEach(participant -> {
				participant.setId(UUID.randomUUID());
				participant.setConversation(conversation);
			});
			return iConversationRepository.save(conversation);
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.CONVERSATION_NOT_ADDED, IErrorDetails.UNABLE_TO_ADD_CONVERSATION);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IConversationService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String conversationId) throws TogetherException {
		try {
			if (isExists(conversationId)) {
				iConversationRepository.delete(UUID.fromString(conversationId));
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID,
					String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IConversationService#isExists(java.
	 * lang.String)
	 */
	@Override
	public boolean isExists(String conversationId) throws TogetherException {
		try {
			return iConversationRepository.exists(UUID.fromString(conversationId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID,
					String.format(IErrorDetails.INVALID_CONVERSATION_ID, conversationId));
		}

	}

}
