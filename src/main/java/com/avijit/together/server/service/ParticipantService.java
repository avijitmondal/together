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

}
