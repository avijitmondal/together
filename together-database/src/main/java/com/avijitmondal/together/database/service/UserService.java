/*****************************************************************************
 * FILE NAME   : UserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.database.service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import com.avijitmondal.together.database.dao.User;
import com.avijitmondal.together.database.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;

/**
 * @author avijit
 *
 */
@Service("userService")
public class UserService implements IUserService {

	/**
	 * 
	 */
	@Autowired
	private IUserRepository iUserRepository;


	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IUserService#getAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public Page<User> findAll(Pageable pageable) {
		try {
			return iUserRepository.findAll(pageable);
		} catch (Exception exception) {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IUserService#findById(java.lang.
	 * String)
	 */
	@Override
	public Optional<User> findById(String userId) throws TogetherException {
		try {
			return iUserRepository.findById(UUID.fromString(userId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_USER_ID, IErrorDetails.INVALID_USER_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IUserService#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String userId) throws TogetherException {
		try {
			if (isExists(userId)) {
				iUserRepository.deleteById(UUID.fromString(userId));
				return true;
			} else {
				return false;
			}
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_USER_ID, IErrorDetails.INVALID_USER_ID);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IUserService#save(com.avijit.together.
	 * server.dao.User)
	 */
	@Override
	public User save(User user) throws TogetherException {
		try {
			user.setId(UUID.randomUUID());
			user.setCreatedAt(LocalDateTime.now());
			user.setUpdatedAt(LocalDateTime.now());
			return iUserRepository.save(user);
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.USER_NOT_ADDED, IErrorDetails.UNABLE_TO_ADD_USER);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IUserService#findByEmail(java.lang.
	 * String)
	 */
	@Override
	public Optional<User> findByEmail(String email) throws TogetherException {
		try {
			return iUserRepository.findByEmail(email);
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.USER_NOT_FOUND,
					String.format(IErrorDetails.USER_EMAIL_NOT_FOUND, email));
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IUserService#isExists(java.lang.String)
	 */
	@Override
	public boolean isExists(String userId) throws TogetherException {
		try {
			return iUserRepository.existsById(UUID.fromString(userId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_USER_ID,
					String.format(IErrorDetails.INVALID_USER_ID, userId));
		}
	}

}
