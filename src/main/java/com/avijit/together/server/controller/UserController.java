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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.avijit.together.server.model.User;
import com.avijit.together.server.service.PageResource;
import com.avijit.together.server.service.UserService;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * @param pageable
	 * @return
	 */
	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public PageResource<User> list(Pageable pageable) {

		Page<User> users = userService.getAll(pageable);
		return new PageResource<User>(users, "page", "size");

	}

	/**
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public User findById(@PathVariable("id") String userId) {
		User user = userService.findById(userId);
		return user;
	}
}
