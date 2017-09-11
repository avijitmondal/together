/*****************************************************************************
 * FILE NAME   : UserController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.User;
import com.avijit.together.server.service.UserService;
import com.avijit.together.server.util.PageResource;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/users")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, produces = { "application/json" })
	public PageResource<User> list(Pageable pageable) {

		Page<User> users = userService.getAll(pageable);
		return new PageResource<User>(users, "page", "size");

	}

	/**
	 * @param userId
	 * @return
	 */
	@RequestMapping(params = "email", method = RequestMethod.GET, produces = { "application/json" })
	public User findByEmail(@RequestParam("email") String email) {
		User user = userService.findByEmail(email);
		return user;
	}

	/**
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{user_id}", method = RequestMethod.GET, produces = { "application/json" })
	public User findById(@PathVariable("user_id") String userId) {
		User user = userService.findById(userId);
		return user;
	}

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = { "application/json" }, produces = { "application/json" })
	public HttpEntity<User> save(@RequestBody User user) {
		User temp = userService.save(user);
		return new ResponseEntity<User>(temp, HttpStatus.CREATED);
	}

	/**
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/{user_id}", method = RequestMethod.DELETE, produces = { "application/json" })
	public HttpEntity<User> delete(@PathVariable("user_id") String userId) {
		boolean result = userService.delete(userId);
		if (result)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
}
