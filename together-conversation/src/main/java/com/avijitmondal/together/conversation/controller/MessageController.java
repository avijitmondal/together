/*****************************************************************************
 * FILE NAME   : MessageController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.conversation.controller;

import javax.servlet.http.HttpServletRequest;

import com.avijitmondal.together.conversation.service.MessageService;
import com.avijitmondal.together.core.data.Constants;
import com.avijitmondal.together.core.dto.Message;
import com.avijitmondal.together.core.dto.ResponseFactory;
import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.core.util.ws.PageResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = Constants.API_MESSAGES)
public class MessageController {

    /**
     *
     */
    @Autowired
    private MessageService messageService;

    /**
     * @param request
     * @param pageable
     * @param conversationId
     * @param messageId
     * @return
     */
    @GetMapping(value = "/{message_id}", produces = {"application/json"}, consumes = {"application/json"})
    public HttpEntity<?> findById(HttpServletRequest request, Pageable pageable,
                                  @PathVariable("conversation_id") String conversationId, @PathVariable("message_id") String messageId) {
        try {
            var message = messageService.findById(conversationId, messageId);
            if (!message.isEmpty()) {
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.MESSAGE_NOT_ADDED,
                    IErrorDetails.UNABLE_TO_ADD_MESSAGE, IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        }
    }

    /**
     * @param request
     * @param pageable
     * @param conversationId
     * @return
     */
    @GetMapping(produces = {"application/json"}, consumes = {"application/json"})
    public PageResource<Message> findByConversationId(HttpServletRequest request, Pageable pageable,
                                                      @PathVariable("conversation_id") String conversationId) {
        try {
            Page<Message> messages = messageService.findByConversationId(pageable, conversationId);
            return new PageResource<>(messages, "page", "size");
        } catch (TogetherException togetherException) {
            return null;
        }
    }

    /**
     * @param request
     * @param conversationId
     * @param messageId
     * @return
     */
    @DeleteMapping(value = "/{message_id}", produces = {"application/json"}, consumes = {"application/json"})
    public HttpEntity<?> delete(HttpServletRequest request, @PathVariable("conversation_id") String conversationId,
                                @PathVariable("message_id") String messageId) {
        try {
            boolean result = messageService.delete(messageId);
            if (result) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.MESSAGE_NOT_FOUND,
                    String.format(IErrorDetails.MESSAGE_ID_NOT_FOUND, messageId), IErrorDetails.ENTER_VALID_MESSAGE_ID,
                    request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.ENTER_VALID_MESSAGE_ID, request.getRequestURI());
        }
    }
}
