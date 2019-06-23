/*****************************************************************************
 * FILE NAME   : IConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import com.avijit.together.core.model.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.core.exception.UUIDConversationException;

import java.util.Optional;

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
	 * @throws TogetherException
	 */
	Page<Conversation> findByUserId(Pageable pageable, String userId) throws TogetherException;

	/**
	 * @param conversationId
	 * @return
	 * @throws TogetherException
	 */
	Optional<Conversation> findById(String conversationId) throws TogetherException;

	/**
	 * @param conversation
	 * @return
	 * @throws TogetherException
	 */
	Conversation save(Conversation conversation) throws TogetherException;

	/**
	 * @param conversationId
	 * @return
	 * @throws TogetherException
	 */
	boolean delete(String conversationId) throws TogetherException;
	
	/**
	 * @param conversationId
	 * @return
	 * @throws UUIDConversationException 
	 */
	boolean isExists(String conversationId) throws TogetherException;
}
