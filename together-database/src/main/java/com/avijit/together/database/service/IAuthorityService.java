/*****************************************************************************
 * FILE NAME   : IAuthorityService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Feb 1, 2018
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import com.avijit.together.database.dao.Authority;

/**
 * @author avijit
 *
 */
public interface IAuthorityService {
	Authority findByAuthorityRoleUSER();

	Authority findByAuthorityRoleADMIN();
}
