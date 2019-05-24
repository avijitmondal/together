/*****************************************************************************
 * FILE NAME   : ParticipantController.java
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
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.dto.ResponseFactory;
import com.avijit.together.server.exception.ErrorCode;
import com.avijit.together.server.exception.IErrorDetails;
import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.Participant;
import com.avijit.together.server.service.ParticipantService;
import com.avijit.together.server.util.PageResource;

import static com.avijit.together.server.data.I_Constant.API_CONVERSATION_PARTICIPANTS;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = API_CONVERSATION_PARTICIPANTS)
public class ParticipantController {

	/**
	 * 
	 */
	@Autowired
	private ParticipantService participantService;

	/**
	 * @param request
	 * @param conversationId
	 * @param participantId
	 * @return
	 */
	@RequestMapping(value = "/{participant_id}", method = RequestMethod.GET, produces = { "application/json" })
	public HttpEntity<?> findById(HttpServletRequest request, @PathVariable("conversation_id") String conversationId,
			@PathVariable("participant_id") String participantId) {
		try {
			Participant participant = participantService.findById(participantId);
			if (null != participant) {
				return new ResponseEntity<>(participant, HttpStatus.OK);
			}
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.INVALID_PARTICIPANT_ID,
					IErrorDetails.INVALID_PARTICIPANT_ID, IErrorDetails.ENTER_VALID_PARTICIPANT_ID,
					request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
					IErrorDetails.ENTER_VALID_PARTICIPANT_ID, request.getRequestURI());
		}
	}

	/**
	 * @param request
	 * @param pageable
	 * @param conversationId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<?> findByConversationId(HttpServletRequest request, Pageable pageable,
			@PathVariable("conversation_id") String conversationId) {
		try {
			Page<Participant> participants = participantService.findByConversationId(pageable, conversationId);
			return new PageResource<Participant>(participants, "page", "size");
		} catch (TogetherException togetherException) {
			return null;
		}
	}

	/**
	 * @param request
	 * @param conversationId
	 * @param participantId
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<?> delete(HttpServletRequest request, @PathVariable("conversation_id") String conversationId,
			@PathVariable("participant_id") String participantId) {
		try {
			boolean result = participantService.delete(participantId);
			if (result) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.INVALID_PARTICIPANT_ID,
					IErrorDetails.INVALID_PARTICIPANT_ID, IErrorDetails.ENTER_VALID_PARTICIPANT_ID,
					request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
					IErrorDetails.ENTER_VALID_PARTICIPANT_ID, request.getRequestURI());
		}
	}

	/**
	 * @param request
	 * @param participant
	 * @param conversationId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public HttpEntity<?> save(HttpServletRequest request, @RequestBody Participant participant,
			@PathVariable("conversation_id") String conversationId) {
		try {
			Participant temp = participantService.save(conversationId, participant);
			if (null != temp) {
				return new ResponseEntity<Participant>(temp, HttpStatus.CREATED);
			}
			return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.PARTICIPANT_NOT_ADDED,
					IErrorDetails.UNABLE_TO_ADD_PARTICIPANT, IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, togetherException,
					IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
		}
	}
}
