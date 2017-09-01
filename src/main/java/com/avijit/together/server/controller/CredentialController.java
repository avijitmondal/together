/*****************************************************************************
 * FILE NAME   : CredentialController.java
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

import com.avijit.together.server.model.Credential;
import com.avijit.together.server.service.CredentialService;
import com.avijit.together.server.service.PageResource;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/authentications")
public class CredentialController {
	@Autowired
	private CredentialService credentialService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Credential> list(Pageable pageable) {

		Page<Credential> credentials = credentialService.findAll(pageable);
		return new PageResource<Credential>(credentials, "page", "size");

	}

	/**
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{authentication_id}", method = RequestMethod.GET, produces = { "application/json" })
	public Credential findById(@PathVariable("authentication_id") String authenticationId) {
		Credential credential = credentialService.findById(authenticationId);
		return credential;
	}

	/**
	 * @param conversation
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = {
			"application/json" }, produces = { "application/json" })
	public HttpEntity<Credential> save(@RequestBody Credential credential) {
		Credential temp = credentialService.save(credential);
		return new ResponseEntity<Credential>(temp, HttpStatus.CREATED);
	}

	/**
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{authentication_id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<Credential> delete(@PathVariable("authentication_id") String authenticationId) {
		boolean result = credentialService.delete(authenticationId);
		if (result)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
