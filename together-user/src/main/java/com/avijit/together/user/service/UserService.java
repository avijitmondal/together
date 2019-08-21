/*****************************************************************************
 * FILE NAME   : UserService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.user.service;

import com.avijit.together.core.data.Constants;
import com.avijit.together.core.dto.ResponseDTO;
import com.avijit.together.core.dto.User;
import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.core.util.parser.GsonParser;
import com.avijit.together.core.ws.HttpMethod;
import com.avijit.together.core.ws.RestService;
import com.google.gson.reflect.TypeToken;
import org.apache.http.HttpStatus;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
            restService.setUrl(Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_USERS);

            restService.execute();

            if (restService.isSuccessResponse(HttpStatus.SC_OK)) {
                String responseAsString = restService.getSuccessResponse();

                return  (ResponseDTO<List<User>>) GsonParser.fromString(responseAsString, new TypeToken<ResponseDTO<List<User>>>() {
                }.getType());
            }
        } catch (Exception exception) {
			return new ResponseDTO<>();
		}
		return new ResponseDTO<>();
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
			RestService restService = new RestService();

			restService.setHttpMethod(HttpMethod.GET);
			restService.isSecured(false);
			restService.setUrl(Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_USERS + "/" + userId);

			restService.execute();

			if (restService.isSuccessResponse(HttpStatus.SC_CREATED)) {
				String responseAsString = restService.getSuccessResponse();
				User response = GsonParser.fromString(responseAsString, User.class);

				return Optional.of(response);
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}
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
			RestService restService = new RestService();

			restService.setHttpMethod(HttpMethod.DELETE);
			restService.isSecured(false);
			restService.setUrl(Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_USERS + "/" + userId);

			restService.execute();

			if (restService.isSuccessResponse(HttpStatus.SC_NO_CONTENT)) {
				return true;
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}
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
			RestService restService = new RestService();

			restService.setHttpMethod(HttpMethod.POST);
			restService.isSecured(false);
			restService.setUrl(Constants.URI_HTTP + Constants.SERVICE_TOGETHER_DATABASE + Constants.API_USERS);

			restService.execute();
			if (restService.isSuccessResponse(HttpStatus.SC_CREATED)) {
				String responseAsString = restService.getSuccessResponse();
				return GsonParser.fromString(responseAsString, User.class);
			} else {
				throw new TogetherException(restService.getErrorResponse());
			}
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
