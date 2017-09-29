/*****************************************************************************
 * FILE NAME   : Message.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.avijit.together.server.util.javatime.LocalDateTimeDeserializer;
import com.avijit.together.server.util.javatime.LocalDateTimeSerializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author avijit
 *
 */
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
	private UUID conversationId;

	/**
	 * Sender ID of the message
	 */
	@Column(name = "SENDER_ID")
	private UUID senderId;

	/**
	 * Message
	 */
	@Column(name = "MESSAGE")
	private String message;

	/**
	 * message type
	 */
	@Column(name = "message_type", columnDefinition = "enum('TEXT','IMAGE','VIDEO','AUDIO','DOCUMENT')")
	@Enumerated(EnumType.STRING)
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

	/**
	 * 
	 */
	public Message() {
		super();
	}

	/**
	 * @param id
	 *            Unique ID for Message
	 * @param conversationId
	 *            Conversation ID message belongs to
	 * @param senderId
	 *            Sender ID of the message
	 * @param message
	 *            Message
	 * @param messageType
	 *            message type
	 * @param attachmentThumbnailUrl
	 *            If image then attachment thumbnail URL
	 * @param attachmentUrl
	 *            If message is document then document attachment URL
	 * @param createdAt
	 *            Message sent DateTime
	 * @param updatedAt
	 *            Last DateTime when the message last updated
	 */
	public Message(UUID id, UUID conversationId, UUID senderId, String message, MessageType messageType,
			String attachmentThumbnailUrl, String attachmentUrl, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.conversationId = conversationId;
		this.senderId = senderId;
		this.message = message;
		this.messageType = messageType;
		this.attachmentThumbnailUrl = attachmentThumbnailUrl;
		this.attachmentUrl = attachmentUrl;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	/**
	 * @return the id
	 */
	public UUID getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(UUID id) {
		this.id = id;
	}

	/**
	 * @return the conversationId
	 */
	public UUID getConversationId() {
		return conversationId;
	}

	/**
	 * @param conversationId
	 *            the conversationId to set
	 */
	public void setConversationId(UUID conversationId) {
		this.conversationId = conversationId;
	}

	/**
	 * @return the senderId
	 */
	public UUID getSenderId() {
		return senderId;
	}

	/**
	 * @param senderId
	 *            the senderId to set
	 */
	public void setSenderId(UUID senderId) {
		this.senderId = senderId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the messageType
	 */
	public MessageType getMessageType() {
		return messageType;
	}

	/**
	 * @param messageType
	 *            the messageType to set
	 */
	public void setMessageType(MessageType messageType) {
		this.messageType = messageType;
	}

	/**
	 * @return the attachmentThumbnailUrl
	 */
	public String getAttachmentThumbnailUrl() {
		return attachmentThumbnailUrl;
	}

	/**
	 * @param attachmentThumbnailUrl
	 *            the attachmentThumbnailUrl to set
	 */
	public void setAttachmentThumbnailUrl(String attachmentThumbnailUrl) {
		this.attachmentThumbnailUrl = attachmentThumbnailUrl;
	}

	/**
	 * @return the attachmentUrl
	 */
	public String getAttachmentUrl() {
		return attachmentUrl;
	}

	/**
	 * @param attachmentUrl
	 *            the attachmentUrl to set
	 */
	public void setAttachmentUrl(String attachmentUrl) {
		this.attachmentUrl = attachmentUrl;
	}

	/**
	 * @return the createdAt
	 */
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	/**
	 * @param createdAt
	 *            the createdAt to set
	 */
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * @return the updatedAt
	 */
	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * @param updatedAt
	 *            the updatedAt to set
	 */
	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

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
