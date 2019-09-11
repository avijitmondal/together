/*****************************************************************************
 * FILE NAME   : IUserRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.repository;

import com.avijit.together.database.dao.User;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

/**
 * @author avijit
 *
 */
@Repository("iUserRepository")
public interface IUserRepository extends PagingAndSortingRepository<User, UUID> {

	/**
	 * @param email
	 * @return
	 */
	Optional<User> findByEmail(String email);

}
