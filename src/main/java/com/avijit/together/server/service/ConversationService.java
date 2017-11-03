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

import com.avijit.together.server.exception.UUIDConversationException;
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
	public Page<Conversation> findByUserId(Pageable pageable, String userId) {
		return iConversationRepository.findByUserId(pageable, UUID.fromString(userId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IConversationService#findById(java.
	 * lang.String)
	 */
	@Override
	public Conversation findById(String conversationId) {
		return iConversationRepository.findOne(UUID.fromString(conversationId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IConversationService#save(com.avijit.
	 * together.server.model.Conversation)
	 */
	@Override
	public Conversation save(Conversation conversation) {
		conversation.setId(UUID.randomUUID());
		conversation.setCreatedAt(LocalDateTime.now());
		conversation.setUpdatedAt(LocalDateTime.now());
		try {
			return iConversationRepository.save(conversation);
		} catch (Exception exception) {
			return null;
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
	public boolean delete(String conversationId) {
		try {
			iConversationRepository.delete(UUID.fromString(conversationId));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IConversationService#isExists(java.
	 * lang.String)
	 */
	@Override
	public boolean isExists(String conversationId) throws UUIDConversationException {
		UUID tempConversationID = null;
		try {
			tempConversationID = UUID.fromString(conversationId);
		} catch (Exception exception) {
			throw new UUIDConversationException("Not a valid Conversation ID");
		}
		return iConversationRepository.exists(tempConversationID);
	}

}
