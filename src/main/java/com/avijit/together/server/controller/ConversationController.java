/*****************************************************************************
 * FILE NAME   : ConversationController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import javax.servlet.http.HttpServletRequest;

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

import com.avijit.together.server.dto.ResponseFactory;
import com.avijit.together.server.exception.ErrorCode;
import com.avijit.together.server.exception.IErrorDetails;
import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.Conversation;
import com.avijit.together.server.service.ConversationService;
import com.avijit.together.server.util.PageResource;

import static com.avijit.together.server.data.I_Constant.API_CONVERSATIONS;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = API_CONVERSATIONS)
public class ConversationController {
	/**
	 * 
	 */
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
	 * @param request
	 * @param conversationId
	 * @return
	 */
	@RequestMapping(value = "/{conversation_id}", method = RequestMethod.GET, produces = { "application/json" })
	public HttpEntity<?> findById(HttpServletRequest request, @PathVariable("conversation_id") String conversationId) {
		try {
			Conversation conversation = conversationService.findById(conversationId);
			if (null != conversation) {
				return new ResponseEntity<>(conversation, HttpStatus.OK);
			}
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.CONVERSATION_NOT_FOUND,
					String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId),
					IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, togetherException,
					IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
		}
	}

	/**
	 * @param pageable
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "user_id", method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Conversation> findByUserId(Pageable pageable, @RequestParam("user_id") String userId) {
		try {
			Page<Conversation> conversations = conversationService.findByUserId(pageable, userId);
			if (null != conversations && conversations.hasContent()) {
				return new PageResource<Conversation>(conversations, "page", "size");
			}
			// TODO: Handle this situation
			return null;
		} catch (TogetherException togetherException) {
			return null;
		}
	}

	/**
	 * @param request
	 * @param conversation
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public HttpEntity<?> save(HttpServletRequest request, @RequestBody Conversation conversation) {
		try {
			Conversation temp = conversationService.save(conversation);
			if (null != temp) {
				return new ResponseEntity<Conversation>(temp, HttpStatus.CREATED);
			}
			return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.CONVERSATION_NOT_ADDED,
					IErrorDetails.UNABLE_TO_ADD_CONVERSATION, IErrorDetails.TRY_SOMETIME_LATER,
					request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, togetherException,
					IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
		}
	}

	/**
	 * @param request
	 * @param conversationId
	 * @return
	 */
	@RequestMapping(value = "/{conversation_id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<?> delete(HttpServletRequest request, @PathVariable("conversation_id") String conversationId) {
		try {
			boolean result = conversationService.delete(conversationId);
			if (result) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.CONVERSATION_NOT_FOUND,
					String.format(IErrorDetails.CONVERSATION_ID_NOT_FOUND, conversationId),
					IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, togetherException,
					IErrorDetails.ENTER_VALID_CONVERSATION_ID, request.getRequestURI());
		}
	}
}
