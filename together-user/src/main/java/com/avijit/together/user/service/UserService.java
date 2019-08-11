/*****************************************************************************
 * FILE NAME   : UserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.user.service;

import com.avijit.together.core.dto.ResponseDTO;
import com.avijit.together.core.dto.User;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.core.util.parser.GsonParser;
import com.avijit.together.core.ws.HttpMethod;
import com.avijit.together.core.ws.RestService;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.util.EntityUtils;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IUserService#getAll(org.
	 * springframework.data.domain.Pageable)
	 */
	@Override
	public ResponseDTO<List<User>> findAll(Pageable pageable) {
		try {
            RestService restService = new RestService();

            restService.setHttpMethod(HttpMethod.GET);
            restService.isSecured(false);
            restService.setUrl("http://127.0.0.1:8885/api/v1/users");

            HttpResponse httpResponse = restService.execute();
            HttpEntity httpEntity = httpResponse.getEntity();
            if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                String responseAsString = EntityUtils.toString(httpEntity);

                ResponseDTO<List<User>> response = (ResponseDTO<List<User>>) GsonParser.fromString(responseAsString, new TypeToken<ResponseDTO<List<User>>>() {
                }.getType());

                return response;
            }
        } catch (Exception exception) {
			return null;
		}
		return null;
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
		    return null;
//			return iUserRepository.findById(UUID.fromString(userId));
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
//				iUserRepository.deleteById(UUID.fromString(userId));
				return false;
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
//			return iUserRepository.save(user);
            return null;
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
	public User findByEmail(String email) throws TogetherException {
		try {
//			return iUserRepository.findByEmail(email);
            return null;
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
//			return iUserRepository.existsById(UUID.fromString(userId));
            return false;
		} catch (IllegalArgumentException illegalArgumentException) {
			throw new TogetherException(ErrorCode.INVALID_USER_ID,
					String.format(IErrorDetails.INVALID_USER_ID, userId));
		}
	}

}
