/*****************************************************************************
 * FILE NAME   : IParticipantService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import com.avijit.together.core.service.IService;
import com.avijit.together.database.dao.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.core.exception.TogetherException;

import java.util.Optional;

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
	 * @throws TogetherException
	 */
	Optional<Participant> findById(String participantId) throws TogetherException;

	/**
	 * @param conversationId
	 * @param participant
	 * @return
	 * @throws TogetherException
	 */
	Participant save(String conversationId, Participant participant) throws TogetherException;

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 * @throws TogetherException
	 */
	Page<Participant> findByConversationId(Pageable pageable, String conversationId) throws TogetherException;
}
