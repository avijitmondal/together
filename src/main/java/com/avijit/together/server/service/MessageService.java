/*****************************************************************************
 * FILE NAME   : MessageService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
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
import com.avijit.together.server.model.Message;
import com.avijit.together.server.repository.IMessageRepository;

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
	 * @see com.avijit.together.server.service.IMessageService#findById(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Message findById(String conversationId, String messageId) throws TogetherException {
		try {
			return iMessageRepository.findOne(UUID.fromString(messageId));
		} catch (IllegalArgumentException illegalArgumentException) {
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
	 * @see com.avijit.together.server.service.IMessageService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String messageId) throws TogetherException {
		try {
			if (isExists(messageId)) {
				iMessageRepository.delete(UUID.fromString(messageId));
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
			return iMessageRepository.findByConversationId(pageable, UUID.fromString(conversationId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		}
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
			return iMessageRepository.exists(UUID.fromString(messageId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_MESSAGE_ID,
					String.format(IErrorDetails.INVALID_MESSAGE_ID, messageId));
		}
	}

}
