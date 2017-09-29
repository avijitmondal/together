/*****************************************************************************
 * FILE NAME   : IConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.server.model.Conversation;

/**
 * @author avijit
 *
 */
public interface IConversationService extends IService {

	/**
	 * @param pageable
	 * @return
	 */
	Page<Conversation> findAll(Pageable pageable);

	/**
	 * @param pageable
	 * @param userId
	 * @return
	 */
	Page<Conversation> findByUserId(Pageable pageable, String userId);

	/**
	 * @param conversationId
	 * @return
	 */
	Conversation findById(String conversationId);

	/**
	 * @param conversation
	 * @return
	 */
	Conversation save(Conversation conversation);

	/**
	 * @param conversationId
	 * @return
	 */
	boolean delete(String conversationId);
}
