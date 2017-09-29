/**
 * 
 */
package com.avijit.together.server.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.Message;
import com.avijit.together.server.service.MessageService;
import com.avijit.together.server.util.IURIConstant;

/**
 * @author avijit
 *
 */
@RestController
public class ChatController {
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
	@MessageMapping(IURIConstant.URI_GROUP_CHAT + "/{chatRoomId}")
	public void handleGroupChat(@Payload Message message, @DestinationVariable("chatRoomId") String chatRoomId,
			MessageHeaders messageHeaders, SimpMessageHeaderAccessor headerAccessor) throws Exception {
		System.out.println(message);
		messageService.save(message);
		this.simpMessagingTemplate.convertAndSend(IURIConstant.URI_TOPIC_GROUP_CHAT_RESPONSE + chatRoomId, message);
	}

	/**
	 * @param message
	 * @param destinationUserId
	 * @param messageHeaders
	 * @param headerAccessor
	 * @throws Exception
	 */
	@MessageMapping(IURIConstant.URI_PERSONAL_CHAT + "/{destinationUserId}")
	public void handlePrivateChat(@Payload Message message,
			@DestinationVariable("destinationUserId") String destinationUserId, MessageHeaders messageHeaders,
			SimpMessageHeaderAccessor headerAccessor) throws Exception {
		System.out.println(message);
		messageService.save(message);
		this.simpMessagingTemplate.convertAndSend(IURIConstant.URI_QUEUE_PERSONAL_CHAT_RESPONSE + destinationUserId,
				message);
	}

	/**
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/sessionId")
	public String sessionId() {
		return this.session.getId();
	}
}
