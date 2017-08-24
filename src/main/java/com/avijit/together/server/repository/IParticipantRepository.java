/*****************************************************************************
 * FILE NAME   : IParticipantRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.Participant;

/**
 * @author avijit
 *
 */
@Repository("iParticipantRepository")
public interface IParticipantRepository extends PagingAndSortingRepository<Participant, UUID> {

}
