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

import com.avijit.together.server.model.Message;
import com.avijit.together.server.repository.IMessageRepository;

/**
 * @author avijit
 *
 */
@Service("messageService")
public class MessageService implements IMessageService {

	@Autowired
	private IMessageRepository iMessageRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IMessageService#findById(java.lang.
	 * String, java.lang.String)
	 */
	@Override
	public Message findById(String conversationId, String messageId) {
		try {
			return iMessageRepository.findOne(UUID.fromString(messageId));
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IMessageService#save(com.avijit.
	 * together.server.model.Message)
	 */
	@Override
	public Message save(Message message) {
		message.setId(UUID.randomUUID());
		message.setCreatedAt(LocalDateTime.now());
		message.setUpdatedAt(LocalDateTime.now());
		try {
			return iMessageRepository.save(message);
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IMessageService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String messageId) {
		try {
			iMessageRepository.delete(UUID.fromString(messageId));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IMessageService#findByConversationId(
	 * org.springframework.data.domain.Pageable, java.lang.String)
	 */
	@Override
	public Page<Message> findByConversationId(Pageable pageable, String conversationId) {
		try {
			return iMessageRepository.findByConversationId(pageable, UUID.fromString(conversationId));
		} catch (Exception exception) {
			return null;
		}
	}

}
