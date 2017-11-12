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

import com.avijit.together.server.exception.TogetherException;
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
	 * @throws TogetherException
	 */
	Participant findById(String participantId) throws TogetherException;

	/**
	 * @param conversationId
	 * @param participant
	 * @return
	 * @throws TogetherException
	 */
	Participant save(String conversationId, Participant participant) throws TogetherException;

	/**
	 * @param participantId
	 * @return
	 * @throws TogetherException
	 */
	boolean delete(String participantId) throws TogetherException;

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 * @throws TogetherException
	 */
	Page<Participant> findByConversationId(Pageable pageable, String conversationId) throws TogetherException;

	/**
	 * @param participantId
	 * @return
	 * @throws TogetherException
	 */
	boolean isExists(String participantId) throws TogetherException;
}
