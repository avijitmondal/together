/*****************************************************************************
 * FILE NAME   : MessageController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.Message;
import com.avijit.together.server.service.MessageService;
import com.avijit.together.server.util.PageResource;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/conversations/{conversation_id}/messages")
public class MessageController {

	@Autowired
	private MessageService messageService;

	/**
	 * @param conversationId
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/{message_id}", method = RequestMethod.GET, produces = { "application/json" })
	public Message findById(Pageable pageable, @PathVariable("conversation_id") String conversationId,
			@PathVariable("message_id") String messageId) {
		Message message = messageService.findById(conversationId, messageId);
		return message;
	}

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Message> findByConversationId(Pageable pageable,
			@PathVariable("conversation_id") String conversationId) {
		Page<Message> messages = messageService.findByConversationId(pageable, conversationId);
		return new PageResource<Message>(messages, "page", "size");
	}

	/**
	 * @param messageId
	 * @return
	 */
	@RequestMapping(value = "/{message_id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<Message> delete(@PathVariable("conversation_id") String conversationId,
			@PathVariable("message_id") String messageId) {
		boolean result = messageService.delete(messageId);
		if (result)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
