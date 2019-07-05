/*****************************************************************************
 * FILE NAME   : Message.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.dao;

import com.avijit.together.core.bean.MessageType;
import com.avijit.together.core.util.javatime.LocalDateTimeDeserializer;
import com.avijit.together.core.util.javatime.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @author avijit
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "MESSAGES")
public class Message {

	/**
	 * Unique ID for Message
	 */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	/**
	 * Conversation ID message belongs to
	 */
	@Column(name = "CONVERSATION_ID")
	@NotBlank(message = "error.conversationid.notblank")
	private UUID conversationId;

	/**
	 * Sender ID of the message
	 */
	@Column(name = "SENDER_ID")
	@NotBlank(message = "error.senderid.notblank")
	private UUID senderId;

	/**
	 * Message
	 */
	@Column(name = "MESSAGE")
	@NotBlank(message = "error.message.notblank")
	@Size(min = 1, max = 256, message = "Message must be between 2 and 30 characters")
	private String msg;

	/**
	 * message type
	 */
	@Column(name = "message_type", columnDefinition = "enum('TEXT','IMAGE','VIDEO','AUDIO','DOCUMENT')")
	@Enumerated(EnumType.STRING)
	@NotBlank(message = "error.messagetype.notblank")
	private MessageType messageType;

	/**
	 * If image then attachment thumbnail URL
	 */
	@Column(name = "attachment_thumb_url")
	private String attachmentThumbnailUrl;

	/**
	 * If message is document then document attachment URL
	 */
	@Column(name = "attachment_url")
	private String attachmentUrl;

	/**
	 * Message sent DateTime
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	/**
	 * Last DateTime when the message last updated
	 */
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;
}
