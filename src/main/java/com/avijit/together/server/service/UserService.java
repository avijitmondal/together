/*****************************************************************************
 * FILE NAME   : UserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import java.time.LocalDateTime;
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
		try {
			return iUserRepository.findAll(pageable);
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.server.service.IUserService#findById(java.lang.
	 * String)
	 */
	@Override
	public User findById(String userId) {
		try {
			return iUserRepository.findOne(UUID.fromString(userId));
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IUserService#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String userId) {
		try {
			iUserRepository.delete(UUID.fromString(userId));
			return true;
		} catch (Exception exception) {
			return false;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IUserService#save(com.avijit.together.
	 * server.model.User)
	 */
	@Override
	public User save(User user) {
		user.setId(UUID.randomUUID());
		user.setCreatedAt(LocalDateTime.now());
		user.setUpdatedAt(LocalDateTime.now());
		try {
			return iUserRepository.save(user);
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.server.service.IUserService#findByEmail(java.lang.
	 * String)
	 */
	@Override
	public User findByEmail(String email) {
		try {
			return iUserRepository.findByEmail(email);
		} catch (Exception exception) {
			return null;
		}
	}

}
