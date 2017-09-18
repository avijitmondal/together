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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.Conversation;
import com.avijit.together.server.model.User;
import com.avijit.together.server.service.ConversationService;
import com.avijit.together.server.util.PageResource;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/conversations")
public class ConversationController {
	@Autowired
	private ConversationService conversationService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Conversation> list(Pageable pageable) {

		Page<Conversation> conversations = conversationService.findAll(pageable);
		return new PageResource<Conversation>(conversations, "page", "size");

	}

	/**
	 * @param conversationId
	 * @return
	 */
	@RequestMapping(value = "/{conversation_id}", method = RequestMethod.GET, produces = { "application/json" })
	public Conversation findById(@PathVariable("conversation_id") String conversationId) {
		Conversation conversation = conversationService.findById(conversationId);
		return conversation;
	}

	/**
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "user_id", method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Conversation> findByUserId(Pageable pageable, @RequestParam("user_id") String userId) {
		Page<Conversation> conversations = conversationService.findByUserId(pageable, userId);
		return new PageResource<Conversation>(conversations, "page", "size");
	}

	/**
	 * @param conversation
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public HttpEntity<Conversation> save(@RequestBody Conversation conversation) {
		Conversation temp = conversationService.save(conversation);
		return new ResponseEntity<Conversation>(temp, HttpStatus.CREATED);
	}

	/**
	 * @param conversationId
	 * @return
	 */
	@RequestMapping(value = "/{conversation_id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<User> delete(@PathVariable("conversation_id") String conversationId) {
		boolean result = conversationService.delete(conversationId);
		if (result)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
