/*****************************************************************************
 * FILE NAME   : ConversationController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.Conversation;
import com.avijit.together.server.service.ConversationService;
import com.avijit.together.server.service.PageResource;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
public class ConversationController {
	@Autowired
	private ConversationService conversationService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/conversations", method = RequestMethod.GET)
	public PageResource<Conversation> list(Pageable pageable) {

		Page<Conversation> conversations = conversationService.findAll(pageable);
		return new PageResource<Conversation>(conversations, "page", "size");

	}

	/**
	 * @param conversationId
	 * @return
	 */
	@RequestMapping(value = "/conversations/{id}", method = RequestMethod.GET)
	public Conversation findById(@PathVariable("id") String conversationId) {
		Conversation conversation = conversationService.findById(conversationId);
		return conversation;
	}
}
