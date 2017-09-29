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
	 */
	Message findById(String conversationId, String messageId);

	/**
	 * @param message
	 * @return
	 */
	Message save(Message message);

	/**
	 * @param messageId
	 * @return
	 */
	boolean delete(String messageId);

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 */
	Page<Message> findByConversationId(Pageable pageable, String conversationId);

}
