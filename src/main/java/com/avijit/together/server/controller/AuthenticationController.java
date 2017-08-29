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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
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
@RequestMapping(value = "/api/authentications")
public class AuthenticationController {
	@Autowired
	private AuthenticationService authenticationService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Authentication> list(Pageable pageable) {

		Page<Authentication> authentications = authenticationService.findAll(pageable);
		return new PageResource<Authentication>(authentications, "page", "size");

	}

	/**
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { "application/json" })
	public Authentication findById(@PathVariable("id") String authenticationId) {
		Authentication authentication = authenticationService.findById(authenticationId);
		return authentication;
	}

	/**
	 * @param conversation
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public HttpEntity<Authentication> save(@RequestBody Authentication authentication) {
		Authentication temp = authenticationService.save(authentication);
		return new ResponseEntity<Authentication>(temp, HttpStatus.CREATED);
	}

	/**
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<Authentication> delete(@PathVariable("id") String authenticationId) {
		boolean result = authenticationService.delete(authenticationId);
		if (result)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
