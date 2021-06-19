/*****************************************************************************
 * FILE NAME   : IAuthorityRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.database.repository;

import com.avijitmondal.together.database.dao.Authority;
import com.avijitmondal.together.core.bean.AuthorityRole;
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
