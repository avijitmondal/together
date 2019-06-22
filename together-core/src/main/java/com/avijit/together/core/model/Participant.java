/*****************************************************************************
 * FILE NAME   : Participant.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
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
@Table(name = "PARTICIPANTS")
public class Participant {

	/**
	 * Unique ID for Participant
	 */
	@Id
	@GeneratedValue(generator = "uuid2")
	@GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(columnDefinition = "BINARY(16)", name = "ID")
	private UUID id;

	/**
	 * Participant user ID
	 */
	@Column(name = "USERS_ID")
	private UUID userId;

	/**
	 * Conversation participant belongs to
	 */
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "CONVERSATION_ID")
	private Conversation conversation;
}
