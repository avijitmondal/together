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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.dto.AuthenticationRequestDTO;
import com.avijit.together.server.dto.AuthenticationResponseDTO;
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
	 * @throws AuthenticationException
	 */
	@RequestMapping(value = "/auth", method = RequestMethod.POST)
	public HttpEntity<AuthenticationResponseDTO> createAuthenticationToken(
			@RequestBody AuthenticationRequestDTO authenticationRequestDTO, Device device)
			throws AuthenticationException {

		// Perform the security
		final Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequestDTO.getUsername(),
						authenticationRequestDTO.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);

		// Reload password post-security so we can generate token
		final CredentialBean userDetails = (CredentialBean) credentialService
				.loadUserByUsername(authenticationRequestDTO.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails, device);
		User user = userService.findByEmail(userDetails.getEmail());

		// Return the token
		return ResponseEntity.ok(new AuthenticationResponseDTO(token, user));
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
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{authentication_id}", method = RequestMethod.GET, produces = { "application/json" })
	public Credential findById(@PathVariable("authentication_id") String authenticationId) {
		Credential credential = credentialService.findById(authenticationId);
		return credential;
	}

	/**
	 * @param credential
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public HttpEntity<Credential> save(@RequestBody Credential credential) {
		Credential temp = credentialService.save(credential);
		return new ResponseEntity<Credential>(temp, HttpStatus.CREATED);
	}

	/**
	 * @param authenticationId
	 * @return
	 */
	@RequestMapping(value = "/{authentication_id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<Credential> delete(@PathVariable("authentication_id") String authenticationId) {
		boolean result = credentialService.delete(authenticationId);
		if (result)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
