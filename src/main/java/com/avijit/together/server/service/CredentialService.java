/*****************************************************************************
 * FILE NAME   : CredentialService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijit.together.server.model.Credential;
import com.avijit.together.server.repository.ICredentialRepository;

/**
 * @author avijit
 *
 */
@Service("credentialService")
public class CredentialService implements IAuthenticationService {

	@Autowired
	private ICredentialRepository iCredentialRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IAuthenticationService#findAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Credential> findAll(Pageable pageable) {
		return iCredentialRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IAuthenticationService#findById(java.
	 * lang.String)
	 */
	@Override
	public Credential findById(String authenticationId) {
		return iCredentialRepository.findOne(UUID.fromString(authenticationId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IAuthenticationService#save(com.avijit
	 * .together.server.model.Authentication)
	 */
	@Override
	public Credential save(Credential credential) {
		credential.setId(UUID.randomUUID());
		credential.setLastPasswordResetDate(new Date());
		credential.setEnabled(true);
		try {
			return iCredentialRepository.save(credential);
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IAuthenticationService#delete(java.
	 * lang.String)
	 */
	@Override
	public boolean delete(String authenticationId) {
		try {
			iCredentialRepository.delete(UUID.fromString(authenticationId));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

}
