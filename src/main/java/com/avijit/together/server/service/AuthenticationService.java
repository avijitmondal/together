/*****************************************************************************
 * FILE NAME   : AuthenticationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijit.together.server.model.Authentication;
import com.avijit.together.server.repository.IAuthenticationRepository;

/**
 * @author avijit
 *
 */
@Service("authenticationService")
public class AuthenticationService implements IAuthenticationService {

	@Autowired
	private IAuthenticationRepository iAuthenticationRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IAuthenticationService#findAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Authentication> findAll(Pageable pageable) {
		return iAuthenticationRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IAuthenticationService#findById(java.
	 * lang.String)
	 */
	@Override
	public Authentication findById(String authenticationId) {
		return iAuthenticationRepository.findOne(UUID.fromString(authenticationId));
	}

}
