/*****************************************************************************
 * FILE NAME   : ConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijit.together.server.model.Conversation;
import com.avijit.together.server.repository.IConversationRepository;

/**
 * @author avijit
 *
 */
@Service("conversationService")
public class ConversationService implements IConversationService {

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
	 * com.avijit.together.server.service.IConversationService#findById(java.
	 * lang.String)
	 */
	@Override
	public Conversation findById(String conversationId) {
		return iConversationRepository.findOne(UUID.fromString(conversationId));
	}

}
