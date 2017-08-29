/*****************************************************************************
 * FILE NAME   : Conversation.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
@Table(name = "CONVERSATION")
public class Conversation {

	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	@Column(name = "TITLE")
	private String title;

	@Column(name = "CREATOR_ID")
	private UUID creatorId;

	@Column(name = "CHANNEL_ID")
	private String channelId;

	@Column(name = "TYPE", columnDefinition = "enum('GROUP','SINGLE')", nullable = false)
	@Enumerated(EnumType.STRING)
	private ConversationType conversationType;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "CREATED_AT")
	private LocalDateTime createdAt;

	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@DateTimeFormat(iso = ISO.DATE_TIME)
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(name = "UPDATED_AT")
	private LocalDateTime updatedAt;

	@OneToMany(targetEntity = Participant.class, mappedBy = "conversation", fetch = FetchType.EAGER)
	private Set<Participant> participants;

	/**
	 * 
	 */
	public Conversation() {
		super();
	}

	/**
	 * @param id
	 * @param title
	 * @param creatorId
	 * @param channelId
	 * @param conversationType
	 * @param createdAt
	 * @param updatedAt
	 * @param participants
	 */
	public Conversation(UUID id, String title, UUID creatorId, String channelId, ConversationType conversationType,
			LocalDateTime createdAt, LocalDateTime updatedAt, Set<Participant> participants) {
		super();
		this.id = id;
		this.title = title;
		this.creatorId = creatorId;
		this.channelId = channelId;
		this.conversationType = conversationType;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.participants = participants;
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
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the creatorId
	 */
	public UUID getCreatorId() {
		return creatorId;
	}

	/**
	 * @param creatorId
	 *            the creatorId to set
	 */
	public void setCreatorId(UUID creatorId) {
		this.creatorId = creatorId;
	}

	/**
	 * @return the channelId
	 */
	public String getChannelId() {
		return channelId;
	}

	/**
	 * @param channelId
	 *            the channelId to set
	 */
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	/**
	 * @return the conversationType
	 */
	public ConversationType getConversationType() {
		return conversationType;
	}

	/**
	 * @param conversationType
	 *            the conversationType to set
	 */
	public void setConversationType(ConversationType conversationType) {
		this.conversationType = conversationType;
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

	/**
	 * @return the participants
	 */
	public Set<Participant> getParticipants() {
		return participants;
	}

	/**
	 * @param participants
	 *            the participants to set
	 */
	public void setParticipants(Set<Participant> participants) {
		this.participants = participants;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Conversation [" + (id != null ? "id=" + id + ", " : "") + (title != null ? "title=" + title + ", " : "")
				+ (creatorId != null ? "creatorId=" + creatorId + ", " : "")
				+ (channelId != null ? "channelId=" + channelId + ", " : "")
				+ (conversationType != null ? "conversationType=" + conversationType + ", " : "")
				+ (createdAt != null ? "createdAt=" + createdAt + ", " : "")
				+ (updatedAt != null ? "updatedAt=" + updatedAt + ", " : "")
				+ (participants != null ? "participants=" + participants : "") + "]";
	}

}
