/*****************************************************************************
 * FILE NAME   : UserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.user.service;

import com.avijitmondal.together.core.dto.ResponseDTO;
import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.core.util.EnvironmentValuesReader;
import com.avijitmondal.together.user.dao.User;
import com.avijitmondal.together.user.repository.UserRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * @author avijit
 *
 */
@Service("userService")
public class UserService implements IUserService {
	private final Log logger = LogFactory.getLog(this.getClass());

	@Autowired
	private UserRepository userRepository;
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IUserService#getAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public ResponseDTO<List<User>> findAll(Pageable pageable) {
		try {
			List<User> users = userRepository.findAll();
			return new ResponseDTO<>(users);
        } catch (Exception exception) {
			return new ResponseDTO<>();
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
			logger.debug("finding user with ID: " + userId);
			return userRepository.findById(UUID.fromString(userId));
		} catch (Exception exception) {
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
			Optional<User> optionalUser = userRepository.findById(UUID.fromString(userId));
			if (optionalUser.isPresent()) {
				userRepository.delete(optionalUser.get());
				return true;
			}
			return false;
		} catch (Exception exception) {
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
			var tmpUser = new User();
			tmpUser.setId(UUID.randomUUID());
			tmpUser.setEmail(user.getEmail());
			tmpUser.setPhone(user.getPhone());
			tmpUser.setFirstName(user.getFirstName());
			tmpUser.setMiddleName(user.getMiddleName());
			tmpUser.setLastName(user.getLastName());
			tmpUser.setGender(user.getGender());
			tmpUser.setBirthday(user.getBirthday());
			tmpUser.setCreatedAt(LocalDateTime.now());
			return userRepository.save(tmpUser);
		} catch (Exception exception) {
			exception.printStackTrace();
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
			return userRepository.findByEmail(email);
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
			return userRepository.existsById(UUID.fromString(userId));
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_USER_ID,
					String.format(IErrorDetails.INVALID_USER_ID, userId));
		}
	}

}
