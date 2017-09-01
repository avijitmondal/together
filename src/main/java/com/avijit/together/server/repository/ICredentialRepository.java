/*****************************************************************************
 * FILE NAME   : ICredentialRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 24, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.Credential;

/**
 * @author avijit
 *
 */
@Repository("iCredentialRepository")
public interface ICredentialRepository extends PagingAndSortingRepository<Credential, UUID> {

}
