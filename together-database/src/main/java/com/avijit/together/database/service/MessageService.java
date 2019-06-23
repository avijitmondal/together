/*****************************************************************************
 * FILE NAME   : MessageService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.avijit.together.core.model.Message;
import com.avijit.together.database.repository.IMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;

/**
 * @author avijit
 *
 */
@Service("messageService")
public class MessageService implements IMessageService {

	/**
	 * 
	 */
	@Autowired
	private IMessageRepository iMessageRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IMessageService#findById(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Optional<Message> findById(String conversationId, String messageId) throws TogetherException {
		try {
			return iMessageRepository.findById(UUID.fromString(messageId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_MESSAGE_ID, IErrorDetails.INVALID_MESSAGE_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IMessageService#save(com.avijit.
	 * together.server.model.Message)
	 */
	@Override
	public Message save(Message message) throws TogetherException {
		try {
			message.setId(UUID.randomUUID());
			message.setCreatedAt(LocalDateTime.now());
			message.setUpdatedAt(LocalDateTime.now());
			return iMessageRepository.save(message);
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.MESSAGE_NOT_ADDED, IErrorDetails.UNABLE_TO_ADD_MESSAGE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IMessageService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String messageId) throws TogetherException {
		try {
			if (isExists(messageId)) {
				iMessageRepository.deleteById(UUID.fromString(messageId));
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
	 * @see com.avijit.together.database.service.IMessageService#findByConversationId(
	 * org.springframework.data.domain.Pageable, java.lang.String)
	 */
	@Override
	public Page<Message> findByConversationId(Pageable pageable, String conversationId) throws TogetherException {
		try {
			return iMessageRepository.findByConversationId(pageable, UUID.fromString(conversationId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IMessageService#isExists(java.lang.String)
	 */
	@Override
	public boolean isExists(String messageId) throws TogetherException {
		try {
			return iMessageRepository.existsById(UUID.fromString(messageId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_MESSAGE_ID,
					String.format(IErrorDetails.INVALID_MESSAGE_ID, messageId));
		}
	}

}
