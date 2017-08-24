/*****************************************************************************
 * FILE NAME   : AuthenticationController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
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

import com.avijit.together.server.model.Authentication;
import com.avijit.together.server.service.AuthenticationService;
import com.avijit.together.server.service.PageResource;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/authentications", method = RequestMethod.GET)
	public PageResource<Authentication> list(Pageable pageable) {

		Page<Authentication> authentications = authenticationService.findAll(pageable);
		return new PageResource<Authentication>(authentications, "page", "size");

	}

	/**
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/authentications/{id}", method = RequestMethod.GET)
	public Authentication findById(@PathVariable("id") String authenticationId) {
		Authentication authentication = authenticationService.findById(authenticationId);
		return authentication;
	}
}
