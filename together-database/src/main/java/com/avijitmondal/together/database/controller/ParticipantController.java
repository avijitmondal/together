/*****************************************************************************
 * FILE NAME   : ParticipantController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.database.controller;

import com.avijitmondal.together.core.dto.ResponseFactory;
import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.core.util.ws.PageResource;
import com.avijitmondal.together.database.dao.Participant;
import com.avijitmondal.together.database.service.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import java.util.Optional;

import static com.avijitmondal.together.core.data.Constants.API_CONVERSATION_PARTICIPANTS;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = API_CONVERSATION_PARTICIPANTS)
public class ParticipantController {

    /**
     *
     */
    @Autowired
    private IParticipantService participantService;

    /**
     * @param request
     * @param conversationId
     * @param participantId
     * @return
     */
    @GetMapping(value = "/{participant_id}", produces = {"application/json"})
    public HttpEntity<?> findById(HttpServletRequest request, @PathVariable("conversation_id") String conversationId,
                                  @PathVariable("participant_id") String participantId) {
        try {
            Optional<Participant> participant = participantService.findById(participantId);
            if (participant.isPresent()) {
                return new ResponseEntity<>(participant.get(), HttpStatus.OK);
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
    @GetMapping(produces = {"application/json"})
    public PageResource<?> findByConversationId(HttpServletRequest request, Pageable pageable,
                                                @PathVariable("conversation_id") String conversationId) {
        try {
            Page<Participant> participants = participantService.findByConversationId(pageable, conversationId);
            return new PageResource<>(participants, "page", "size");
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
    @DeleteMapping(value = "/{id}", produces = {"application/json"})
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
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public HttpEntity<?> save(HttpServletRequest request, @RequestBody Participant participant,
                              @PathVariable("conversation_id") String conversationId) {
        try {
            Participant temp = participantService.save(conversationId, participant);
            if (null != temp) {
                return new ResponseEntity<>(temp, HttpStatus.CREATED);
            }
            return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.PARTICIPANT_NOT_ADDED,
                    IErrorDetails.UNABLE_TO_ADD_PARTICIPANT, IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, togetherException,
                    IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        }
    }
}
