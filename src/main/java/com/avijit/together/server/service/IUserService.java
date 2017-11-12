/*****************************************************************************
 * FILE NAME   : IUserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.User;

/**
 * @author avijit
 *
 */
public interface IUserService extends IService {
	/**
	 * @param pageable
	 * @return
	 */
	Page<User> getAll(Pageable pageable);

	/**
	 * @param userId
	 * @return
	 * @throws TogetherException
	 */
	User findById(String userId) throws TogetherException;

	/**
	 * @param userId
	 * @return
	 * @throws TogetherException
	 */
	boolean delete(String userId) throws TogetherException;

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

	/**
	 * @param userId
	 * @return
	 * @throws TogetherException
	 */
	boolean isExists(String userId) throws TogetherException;
}
