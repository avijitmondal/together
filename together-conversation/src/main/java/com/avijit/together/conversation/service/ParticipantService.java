/*****************************************************************************
 * FILE NAME   : ParticipantService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.conversation.service;

import java.util.UUID;

import com.avijit.together.core.dto.Participant;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author avijit
 *
 */
@Service("participantService")
public class ParticipantService implements IParticipantService {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IParticipantService#findAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Participant> findAll(Pageable pageable) {
//		return iParticipantRepository.findAll(pageable);
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IParticipantService#findById(java.lang
	 * .String)
	 */
	@Override
	public Participant findById(String participantId) throws TogetherException {
		try {
//			return iParticipantRepository.findOne(UUID.fromString(participantId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_PARTICIPANT_ID, IErrorDetails.INVALID_PARTICIPANT_ID);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IParticipantService#save(java.lang.
	 * String, com.avijit.together.server.model.Participant)
	 */
	@Override
	public Participant save(String conversationId, Participant participant) throws TogetherException {
		try {
			participant.setId(UUID.randomUUID());
//			participant.setConversationId(UUID.fromString(conversationId));
//			return iParticipantRepository.save(participant);
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.PARTICIPANT_NOT_ADDED, IErrorDetails.UNABLE_TO_ADD_PARTICIPANT);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IParticipantService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String participantId) throws TogetherException {
		try {
			if (isExists(participantId)) {
//				iParticipantRepository.delete(UUID.fromString(participantId));
				return true;
			} else {
				return false;
			}
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_PARTICIPANT_ID, IErrorDetails.INVALID_PARTICIPANT_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IParticipantService#
	 * findByConversationId(org.springframework.data.domain.Pageable,
	 * java.lang.String)
	 */
	@Override
	public Page<Participant> findByConversationId(Pageable pageable, String conversationId) throws TogetherException {
		try {
//			return iParticipantRepository.findByConversationId(pageable, UUID.fromString(conversationId));
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.INVALID_CONVERSATION_ID, IErrorDetails.INVALID_CONVERSATION_ID);
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IParticipantService#isExists(java.lang.
	 * String)
	 */
	@Override
	public boolean isExists(String participantId) throws TogetherException {
		try {
//			return iParticipantRepository.exists(UUID.fromString(participantId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_PARTICIPANT_ID,
					String.format(IErrorDetails.INVALID_PARTICIPANT_ID, participantId));
		}
		return false;
	}

}
