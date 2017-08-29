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

	Page<Conversation> findAll(Pageable pageable);

	Page<Conversation> findByUserId(Pageable pageable, String conversationId, String userId);

	Conversation findById(String conversationId);

	Conversation save(Conversation conversation);

	boolean delete(String conversationId);
}
