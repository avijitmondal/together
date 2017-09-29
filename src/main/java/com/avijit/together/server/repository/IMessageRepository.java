/*****************************************************************************
 * FILE NAME   : IMessageRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.avijit.together.server.model.Message;

/**
 * @author avijit
 *
 */
@Repository("iMessageRepository")
public interface IMessageRepository extends PagingAndSortingRepository<Message, UUID> {

	/**
	 * @param pageable
	 * @param conversationId
	 * @return
	 */
	Page<Message> findByConversationId(Pageable pageable, UUID conversationId);
}
