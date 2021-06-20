/*****************************************************************************
 * FILE NAME   : IParticipantRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.database.repository;

import com.avijitmondal.together.database.dao.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * @author avijit
 *
 */
@Repository("iParticipantRepository")
public interface IParticipantRepository extends PagingAndSortingRepository<Participant, UUID> {

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 */
	Page<Participant> findByConversationId(Pageable pageable, UUID conversationId);
}
