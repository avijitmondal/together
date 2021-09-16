/*****************************************************************************
 * FILE NAME   : Participant.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.dto;

import lombok.*;

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
public class Participant {

	/**
	 * Unique ID for Participant
	 */
	private UUID id;

	/**
	 * Participant user ID
	 */
	private UUID userId;
}
