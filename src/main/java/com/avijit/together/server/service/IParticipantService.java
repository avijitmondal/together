/*****************************************************************************
 * FILE NAME   : IParticipantService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.server.model.Participant;

/**
 * @author avijit
 *
 */
public interface IParticipantService extends IService {

	/**
	 * @param pageable
	 * @return
	 */
	Page<Participant> findAll(Pageable pageable);

	/**
	 * @param participantId
	 * @return
	 */
	Participant findById(String participantId);

	/**
	 * @param conversationId
	 * @param participant
	 * @return
	 */
	Participant save(String conversationId, Participant participant);

	/**
	 * @param participantId
	 * @return
	 */
	boolean delete(String participantId);

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 */
	Page<Participant> findByConversationId(Pageable pageable, String conversationId);
}
