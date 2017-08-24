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
	Page<User> getAll(Pageable pageable);

	User findById(String userId);
}
