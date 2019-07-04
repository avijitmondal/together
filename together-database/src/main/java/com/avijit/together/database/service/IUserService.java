/*****************************************************************************
 * FILE NAME   : IUserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import com.avijit.together.database.dao.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.core.exception.TogetherException;

import java.util.Optional;

/**
 * @author avijit
 *
 */
public interface IUserService extends IService {
	/**
	 * @param pageable
	 * @return
	 */
	Page<User> findAll(Pageable pageable);

	/**
	 * @param userId
	 * @return
	 * @throws TogetherException
	 */
	Optional<User> findById(String userId) throws TogetherException;

	/**
	 * @param user
	 * @return
	 * @throws TogetherException
	 */
	User save(User user) throws TogetherException;

	/**
	 * @param email
	 * @return
	 * @throws TogetherException
	 */
	User findByEmail(String email) throws TogetherException;

}
