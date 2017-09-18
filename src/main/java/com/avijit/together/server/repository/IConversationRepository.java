/*****************************************************************************
 * FILE NAME   : IConversationRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.Conversation;

/**
 * @author avijit
 *
 */
@Repository("iConversationRepository")
public interface IConversationRepository extends PagingAndSortingRepository<Conversation, UUID> {

	@Query(value = "SELECT c FROM Conversation AS c LEFT JOIN c.participants AS p WHERE c.conversationType='SINGLE' OR (c.conversationType='GROUP' AND p.userId=?1)")
	Page<Conversation> findByUserId(Pageable pageable, UUID userId);

}
