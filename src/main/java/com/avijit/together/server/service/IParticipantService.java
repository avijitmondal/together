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

	Page<Participant> findAll(Pageable pageable);

	Participant findById(String participantId);

	Participant save(String conversationId, Participant participant);

	boolean delete(String participantId);

	Page<Participant> findByConversationId(Pageable pageable, String conversationId);
}
