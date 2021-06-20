/*****************************************************************************
 * FILE NAME   : IMessageRepository.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 29, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.database.repository;

import com.avijitmondal.together.database.dao.Message;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

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
