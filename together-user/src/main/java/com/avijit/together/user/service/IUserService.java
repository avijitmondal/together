/*****************************************************************************
 * FILE NAME   : IUserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.user.service;

import com.avijit.together.core.dto.ResponseDTO;
import com.avijit.together.core.dto.User;
import com.avijit.together.core.exception.TogetherException;

import org.springframework.data.domain.Pageable;

import java.util.List;
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
    ResponseDTO<List<User>> findAll(Pageable pageable);

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
