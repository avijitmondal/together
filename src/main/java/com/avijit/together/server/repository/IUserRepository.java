/*****************************************************************************
 * FILE NAME   : IUserRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.User;

/**
 * @author avijit
 *
 */
@Repository("iUserRepository")
public interface IUserRepository extends PagingAndSortingRepository<User, UUID> {

	User findByEmail(String email);
	
}
