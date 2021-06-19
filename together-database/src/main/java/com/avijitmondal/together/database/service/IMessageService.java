/*****************************************************************************
 * FILE NAME   : IMessageService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.database.service;

import com.avijitmondal.together.core.service.IService;
import com.avijitmondal.together.database.dao.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijitmondal.together.core.exception.TogetherException;

import java.util.Optional;

/**
 * @author avijit
 *
 */
public interface IMessageService extends IService {

	/**
	 * @param conversationId
	 * @param messageId
	 * @return
	 * @throws TogetherException
	 */
	Optional<Message> findById(String conversationId, String messageId) throws TogetherException;

	/**
	 * @param message
	 * @return
	 * @throws TogetherException
	 */
	Message save(Message message) throws TogetherException;

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 * @throws TogetherException
	 */
	Page<Message> findByConversationId(Pageable pageable, String conversationId) throws TogetherException;
}
