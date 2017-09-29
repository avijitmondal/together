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
	 */
	User findById(String userId);

	/**
	 * @param userId
	 * @return
	 */
	boolean delete(String userId);

	/**
	 * @param user
	 * @return
	 */
	User save(User user);

	/**
	 * @param email
	 * @return
	 */
	User findByEmail(String email);

}
