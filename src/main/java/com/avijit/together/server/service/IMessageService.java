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
	
	Message findById(String conversationId, String messageId);

	Message save(Message message);

	boolean delete(String messageId);

	Page<Message> findByConversationId(Pageable pageable, String conversationId);
	
}
