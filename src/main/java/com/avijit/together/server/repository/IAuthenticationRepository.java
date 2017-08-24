/*****************************************************************************
 * FILE NAME   : IAuthenticationRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.Authentication;

/**
 * @author avijit
 *
 */
@Repository("iAuthenticationRepository")
public interface IAuthenticationRepository extends PagingAndSortingRepository<Authentication, UUID> {

}
