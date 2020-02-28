/*****************************************************************************
 * FILE NAME   : Message.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.avijit.together.core.util.javatime.LocalDateTimeDeserializer;
import com.avijit.together.core.util.javatime.LocalDateTimeSerializer;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author avijit
 *
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	/**
	 * Unique ID for Message
	 */
	private UUID id;

	/**
	 * Conversation ID message belongs to
	 */
	@NotBlank(message = "error.conversationid.notblank")
	private UUID conversationId;

	/**
	 * Sender ID of the message
	 */
	@NotBlank(message = "error.senderid.notblank")
	private UUID senderId;

	/**
	 * Message
	 */
	@NotBlank(message = "error.message.notblank")
	@Size(min = 1, max = 256, message = "Message must be between 2 and 30 characters")
	private String message;

	/**
	 * message type
	 */
	@Enumerated(EnumType.STRING)
	@NotBlank(message = "error.messagetype.notblank")
	private MessageType messageType;

	/**
	 * If image then attachment thumbnail URL
	 */
	private String attachmentThumbnailUrl;

	/**
	 * If message is document then document attachment URL
	 */
	private String attachmentUrl;

	/**
	 * Message sent DateTime
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime createdAt;

	/**
	 * Last DateTime when the message last updated
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	private LocalDateTime updatedAt;

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Message [" + (id != null ? "id=" + id + ", " : "")
				+ (conversationId != null ? "conversationId=" + conversationId + ", " : "")
				+ (senderId != null ? "senderId=" + senderId + ", " : "")
				+ (message != null ? "message=" + message + ", " : "")
				+ (messageType != null ? "messageType=" + messageType + ", " : "")
				+ (attachmentThumbnailUrl != null ? "attachmentThumbnailUrl=" + attachmentThumbnailUrl + ", " : "")
				+ (attachmentUrl != null ? "attachmentUrl=" + attachmentUrl + ", " : "")
				+ (createdAt != null ? "createdAt=" + createdAt + ", " : "")
				+ (updatedAt != null ? "updatedAt=" + updatedAt : "") + "]";
	}

}
