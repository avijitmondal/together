/*****************************************************************************
 * FILE NAME   : IAuthorityRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.Authority;

/**
 * @author avijit
 *
 */
@Repository("iAuthorityRepository")
public interface IAuthorityRepository extends PagingAndSortingRepository<Authority, UUID> {

}
