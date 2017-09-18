/*****************************************************************************
 * FILE NAME   : ParticipantService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijit.together.server.model.Participant;
import com.avijit.together.server.repository.IParticipantRepository;

/**
 * @author avijit
 *
 */
@Service("participantService")
public class ParticipantService implements IParticipantService {

	@Autowired
	private IParticipantRepository iParticipantRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IParticipantService#findAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Participant> findAll(Pageable pageable) {
		return iParticipantRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IParticipantService#findById(java.lang
	 * .String)
	 */
	@Override
	public Participant findById(String participantId) {
		return iParticipantRepository.findOne(UUID.fromString(participantId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IParticipantService#save(java.lang.
	 * String, com.avijit.together.server.model.Participant)
	 */
	@Override
	public Participant save(String conversationId, Participant participant) {
		participant.setId(UUID.randomUUID());
		participant.setConversationId(UUID.fromString(conversationId));
		try {
			return iParticipantRepository.save(participant);
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IParticipantService#delete(java.lang.
	 * String)
	 */
	@Override
	public boolean delete(String participantId) {
		try {
			iParticipantRepository.delete(UUID.fromString(participantId));
			return true;
		} catch (Exception exception) {
			return false;
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
	public Page<Participant> findByConversationId(Pageable pageable, String conversationId) {
		try {
			return iParticipantRepository.findByConversationId(pageable, UUID.fromString(conversationId));
		} catch (Exception exception) {
			return null;
		}
	}

}
