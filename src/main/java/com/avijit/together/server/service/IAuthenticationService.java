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

import com.avijit.together.server.model.Authentication;

/**
 * @author avijit
 *
 */
public interface IAuthenticationService extends IService{

	Page<Authentication> findAll(Pageable pageable);

	Authentication findById(String authenticationId);
	
	Authentication save(Authentication authentication);

	boolean delete(String authenticationId);
}
