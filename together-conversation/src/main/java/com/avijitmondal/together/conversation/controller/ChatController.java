/**
 * 
 */
package com.avijitmondal.together.conversation.controller;

import javax.servlet.http.HttpSession;

import com.avijitmondal.together.conversation.service.MessageService;
import com.avijitmondal.together.core.data.Constants;
import com.avijitmondal.together.core.dto.Message;
import com.avijitmondal.together.core.exception.TogetherException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author avijit
 *
 */
@RestController
public class ChatController {
	private final Log logger = LogFactory.getLog(this.getClass());

	/**
	 * 
	 */
	@Autowired
	private HttpSession session;
	/**
	 * 
	 */
	@Autowired
	private MessageService messageService;
	/**
	 * 
	 */
	@Autowired
	private SimpMessagingTemplate simpMessagingTemplate;

	/**
	 * @param message
	 * @param chatRoomId
	 * @param messageHeaders
	 * @param headerAccessor
	 * @throws Exception
	 */
	@MessageMapping(Constants.URI_GROUP_CHAT + "/{chatRoomId}")
	public void handleGroupChat(@Payload Message message, @DestinationVariable("chatRoomId") String chatRoomId,
			MessageHeaders messageHeaders, SimpMessageHeaderAccessor headerAccessor) {
		Message temp = null;
		try {
			temp = messageService.save(message);
		} catch (TogetherException togetherException) {
			logger.error(togetherException.getErrorDetails());
		}
		this.simpMessagingTemplate.convertAndSend(Constants.URI_TOPIC_GROUP_CHAT_RESPONSE + chatRoomId, temp);
	}

	/**
	 * @param message
	 * @param destinationUserId
	 * @param messageHeaders
	 * @param headerAccessor
	 * @throws Exception
	 */
	@MessageMapping(Constants.URI_PERSONAL_CHAT + "/{destinationUserId}")
	public void handlePrivateChat(@Payload Message message,
			@DestinationVariable("destinationUserId") String destinationUserId, MessageHeaders messageHeaders,
			SimpMessageHeaderAccessor headerAccessor) {
		Message temp = null;
		try {
			temp = messageService.save(message);
		} catch (TogetherException togetherException) {
			logger.error(togetherException.getErrorDetails());
		}
		this.simpMessagingTemplate.convertAndSend(Constants.URI_QUEUE_PERSONAL_CHAT_RESPONSE + destinationUserId,
				temp);
	}

	/**
	 * @return
	 */
	@ResponseBody
	@GetMapping(value = "/sessionId")
	public String sessionId() {
		return this.session.getId();
	}
}
