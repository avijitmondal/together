/*****************************************************************************
 * FILE NAME   : UserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijit.together.server.model.User;
import com.avijit.together.server.repository.IUserRepository;

/**
 * @author avijit
 *
 */
@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private IUserRepository iUserRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IUserService#getAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<User> getAll(Pageable pageable) {
		return iUserRepository.findAll(pageable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IUserService#findById(java.lang.
	 * String)
	 */
	@Override
	public User findById(String userId) {
		return iUserRepository.findOne(UUID.fromString(userId));
	}

}
