/*****************************************************************************
 * FILE NAME   : ConversationController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.conversation.controller;

import com.avijitmondal.together.conversation.service.IConversationService;
import com.avijitmondal.together.core.dto.Conversation;
import com.avijitmondal.together.core.dto.ResponseDTO;
import com.avijitmondal.together.core.dto.ResponseFactory;
import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

import static com.avijitmondal.together.core.data.Constants.API_CONVERSATIONS;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = API_CONVERSATIONS)
public class ConversationController {
    /**
     *
     */
    @Autowired
    private IConversationService conversationService;

    /**
     * @param pageable
     * @return
     */
    @GetMapping(produces = {"application/json"}, consumes = {"application/json"})
    public ResponseDTO<List<Conversation>> findAll(Pageable pageable) {
        return conversationService.findAll(pageable);
    }

    /**
     * @param request
     * @param conversationId
     * @return
     */
    @GetMapping(value = "/{conversation_id}", produces = {"application/json"}, consumes = {"application/json"})
    public HttpEntity<?> findById(HttpServletRequest request, @PathVariable("conversation_id") String conversationId) {
        try {
            Optional<Conversation> conversation = conversationService.findById(conversationId);
            if (conversation.isPresent()) {
                return new ResponseEntity<>(conversation.get(), HttpStatus.OK);
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
    @GetMapping(params = "user_id", produces = {"application/json"}, consumes = {"application/json"})
    public ResponseDTO<List<Conversation>> findByUserId(Pageable pageable, @RequestParam("user_id") String userId) {
        try {
            return conversationService.findByUserId(pageable, userId);
        } catch (TogetherException togetherException) {
            return new ResponseDTO<>();
        }
    }

    /**
     * @param request
     * @param conversation
     * @return
     */
    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
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
    @DeleteMapping(value = "/{conversation_id}", produces = {"application/json"}, consumes = {"application/json"})
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
