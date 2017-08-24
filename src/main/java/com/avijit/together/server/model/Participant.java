/*****************************************************************************
 * FILE NAME   : Participant.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author avijit
 *
 */
@Entity
@Table(name = "PARTICIPANTS")
public class Participant {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	@Column(name = "CONVERSATION_ID")
	private UUID conversationId;

	@Column(name = "USERS_ID")
	private UUID userId;

	@ManyToOne
	@JoinColumn(name = "ID", insertable = false, updatable = false)
	private Conversation conversation;

	/**
	 * 
	 */
	public Participant() {
		super();
	}

	/**
	 * @param id
	 * @param conversationId
	 * @param userId
	 * @param conversation
	 */
	public Participant(UUID id, UUID conversationId, UUID userId, Conversation conversation) {
		super();
		this.id = id;
		this.conversationId = conversationId;
		this.userId = userId;
		this.conversation = conversation;
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
	 * @return the userId
	 */
	public UUID getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	/**
	 * @return the conversation
	 */
	public Conversation getConversation() {
		return conversation;
	}

	/**
	 * @param conversation
	 *            the conversation to set
	 */
	public void setConversation(Conversation conversation) {
		this.conversation = conversation;
	}

}
