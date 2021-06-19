/*****************************************************************************
 * FILE NAME   : IConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.database.service;

import com.avijitmondal.together.core.service.IService;
import com.avijitmondal.together.database.dao.Conversation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijitmondal.together.core.exception.TogetherException;

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
}
