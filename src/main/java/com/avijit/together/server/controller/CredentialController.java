/*****************************************************************************
 * FILE NAME   : CredentialController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.dto.AuthenticationRequestDTO;
import com.avijit.together.server.dto.AuthenticationResponseDTO;
import com.avijit.together.server.dto.ResponseFactory;
import com.avijit.together.server.exception.ErrorCode;
import com.avijit.together.server.exception.IErrorDetails;
import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.Credential;
import com.avijit.together.server.model.User;
import com.avijit.together.server.model.bean.CredentialBean;
import com.avijit.together.server.security.JwtTokenUtil;
import com.avijit.together.server.service.CredentialService;
import com.avijit.together.server.service.UserService;
import com.avijit.together.server.util.PageResource;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/authentications")
public class CredentialController {
	/**
	 * 
	 */
	@Autowired
	private CredentialService credentialService;
	/**
	 * 
	 */
	@Autowired
	private UserService userService;
	/**
	 * 
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	/**
	 * 
	 */
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	/**
	 * 
	 */
	private String tokenHeader = "Authorization";

	/**
	 * @param authenticationRequestDTO
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public HttpEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequestDTO authenticationRequestDTO,
			Device device) {

		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
						authenticationRequestDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final CredentialBean userDetails = (CredentialBean) credentialService
				.loadUserByUsername(authenticationRequestDTO.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails, device);
		try {
			User user = userService.findByEmail(userDetails.getEmail());
			if (null != user) {
				// Return the token
				return new ResponseEntity<>(new AuthenticationResponseDTO(token, user), HttpStatus.OK);
			}

		} catch (TogetherException togetherException) {

		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	/**
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/refresh", method = RequestMethod.GET)
	public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
		String token = request.getHeader(tokenHeader);
		String username = jwtTokenUtil.getUsernameFromToken(token);
		CredentialBean user = (CredentialBean) credentialService.loadUserByUsername(username);

		if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
			String refreshedToken = jwtTokenUtil.refreshToken(token);
			return ResponseEntity.ok(new AuthenticationResponseDTO(refreshedToken, null));
		} else {
			return ResponseEntity.badRequest().body(null);
		}
	}

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<Credential> list(Pageable pageable) {

		Page<Credential> credentials = credentialService.findAll(pageable);
		return new PageResource<Credential>(credentials, "page", "size");

	}

	/**
	 * @param request
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{authentication_id}", method = RequestMethod.GET, produces = { "application/json" })
	public HttpEntity<?> findById(HttpServletRequest request,
			@PathVariable("authentication_id") String authenticationId) throws TogetherException {
		try {
			Credential credential = credentialService.findById(authenticationId);
			if (null != credential) {
				return new ResponseEntity<>(credential, HttpStatus.OK);
			}
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.AUTHENTICATION_NOT_FOUND,
					String.format(IErrorDetails.AUTHENTICATION_ID_NOT_FOUND, authenticationId),
					IErrorDetails.ENTER_VALID_AUTHENTICATION_ID, request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, togetherException,
					IErrorDetails.ENTER_VALID_AUTHENTICATION_ID, request.getRequestURI());
		}
	}

	/**
	 * @param request
	 * @param credential
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public HttpEntity<?> save(HttpServletRequest request, @RequestBody Credential credential) throws TogetherException {
		try {
			Credential temp = credentialService.save(credential);
			if (null != temp) {
				return new ResponseEntity<Credential>(temp, HttpStatus.CREATED);
			}
			return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.AUTHENTICATION_NOT_ADDED,
					IErrorDetails.UNABLE_TO_ADD_AUTHENTICATION, IErrorDetails.TRY_SOMETIME_LATER,
					request.getRequestURI());
		} catch (TogetherException togetherException) {
			return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, togetherException,
					IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
		}
	}

	/**
	 * @param request
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{authentication_id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<?> delete(HttpServletRequest request, @PathVariable("authentication_id") String authenticationId)
			throws TogetherException {
		try {
			boolean result = credentialService.delete(authenticationId);
			if (result) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.AUTHENTICATION_NOT_FOUND,
					String.format(IErrorDetails.AUTHENTICATION_ID_NOT_FOUND, authenticationId),
					IErrorDetails.ENTER_VALID_AUTHENTICATION_ID, request.getRequestURI());
		} catch (TogetherException togetherException) {

		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
