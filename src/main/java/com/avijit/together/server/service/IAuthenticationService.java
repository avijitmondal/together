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
public interface IAuthenticationService extends IService {

	/**
	 * @param pageable
	 * @return
	 */
	Page<Credential> findAll(Pageable pageable);

	/**
	 * @param authenticationId
	 * @return
	 */
	Credential findById(String authenticationId);

	/**
	 * @param credential
	 * @return
	 */
	Credential save(Credential credential);

	/**
	 * @param authenticationId
	 * @return
	 */
	boolean delete(String authenticationId);
}
