/*****************************************************************************
 * FILE NAME   : IAuthorityRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.repository;

import com.avijit.together.database.dao.Authority;
import com.avijit.together.database.dao.AuthorityRole;
import org.springframework.data.repository.Repository;

import java.util.List;
import java.util.UUID;

/**
 * @author avijit
 *
 */
@org.springframework.stereotype.Repository("iAuthorityRepository")
public interface IAuthorityRepository extends Repository<Authority, UUID> {
	List<Authority> findByAuthorityRole(AuthorityRole authorityRole);
}
