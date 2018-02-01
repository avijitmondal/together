/*****************************************************************************
 * FILE NAME   : IAuthorityRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.Repository;

import com.avijit.together.server.model.Authority;
import com.avijit.together.server.model.AuthorityRole;

/**
 * @author avijit
 *
 */
@org.springframework.stereotype.Repository("iAuthorityRepository")
public interface IAuthorityRepository extends Repository<Authority, UUID> {
	List<Authority> findByAuthorityRole(AuthorityRole authorityRole);
}
