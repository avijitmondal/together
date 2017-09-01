/*****************************************************************************
 * FILE NAME   : IAuthenticationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.server.model.Credential;

/**
 * @author avijit
 *
 */
public interface IAuthenticationService extends IService{

	Page<Credential> findAll(Pageable pageable);

	Credential findById(String authenticationId);
	
	Credential save(Credential credential);

	boolean delete(String authenticationId);
}
