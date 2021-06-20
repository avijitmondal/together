/*****************************************************************************
 * FILE NAME   : IAuthenticationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.database.service;

import com.avijitmondal.together.core.service.IService;
import com.avijitmondal.together.database.dao.Credential;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijitmondal.together.core.exception.TogetherException;

import java.util.Optional;

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
	 * @throws TogetherException
	 */
	Optional<Credential> findById(String authenticationId) throws TogetherException;

	/**
	 * @param credential
	 * @return
	 * @throws TogetherException
	 */
	Credential save(Credential credential) throws TogetherException;
}
