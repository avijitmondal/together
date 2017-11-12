/*****************************************************************************
 * FILE NAME   : IMessageService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.Message;

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
	Message findById(String conversationId, String messageId) throws TogetherException;

	/**
	 * @param message
	 * @return
	 * @throws TogetherException
	 */
	Message save(Message message) throws TogetherException;

	/**
	 * @param messageId
	 * @return
	 * @throws TogetherException
	 */
	boolean delete(String messageId) throws TogetherException;

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 * @throws TogetherException
	 */
	Page<Message> findByConversationId(Pageable pageable, String conversationId) throws TogetherException;

	/**
	 * @param messageId
	 * @return
	 * @throws TogetherException
	 */
	boolean isExists(String messageId) throws TogetherException;

}
