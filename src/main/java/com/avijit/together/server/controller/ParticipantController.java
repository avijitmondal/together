/*****************************************************************************
 * FILE NAME   : ParticipantController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.Participant;
import com.avijit.together.server.service.PageResource;
import com.avijit.together.server.service.ParticipantService;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
public class ParticipantController {
	
	@Autowired
	private ParticipantService participantService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/participants", method = RequestMethod.GET)
	public PageResource<Participant> list(Pageable pageable) {

		Page<Participant> participants = participantService.findAll(pageable);
		return new PageResource<Participant>(participants, "page", "size");

	}

	/**
	 * @param participantId
	 * @return
	 */
	@RequestMapping(value = "/participants/{id}", method = RequestMethod.GET)
	public Participant findById(@PathVariable("id") String participantId) {
		Participant participant = participantService.findById(participantId);
		return participant;
	}
}
