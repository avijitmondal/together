/*****************************************************************************
 * FILE NAME   : IAuthorityService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Feb 1, 2018
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import com.avijit.together.server.model.Authority;

/**
 * @author avijit
 *
 */
public interface IAuthorityService {
	public Authority findByAuthorityRoleUSER();

	public Authority findByAuthorityRoleADMIN();
}
