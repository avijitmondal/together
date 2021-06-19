/*****************************************************************************
 * FILE NAME   : AuthorityService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Feb 1, 2018
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.database.service;

import com.avijitmondal.together.database.dao.Authority;
import com.avijitmondal.together.core.bean.AuthorityRole;
import com.avijitmondal.together.database.repository.IAuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author avijit
 *
 */
@Service("authorityService")
public class AuthorityService implements IAuthorityService {

	@Autowired
	private IAuthorityRepository iAuthorityRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IAuthorityService#findByAuthorityRoleUSER(
	 * )
	 */
	@Override
	public Authority findByAuthorityRoleUSER() {
		return iAuthorityRepository.findByAuthorityRole(AuthorityRole.ROLE_USER).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IAuthorityService#findByAuthorityRoleADMIN
	 * ()
	 */
	@Override
	public Authority findByAuthorityRoleADMIN() {
		return iAuthorityRepository.findByAuthorityRole(AuthorityRole.ROLE_ADMIN).get(0);
	}

}
