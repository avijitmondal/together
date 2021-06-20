/*****************************************************************************
 * FILE NAME   : IConversationService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.ftp.service;

import com.avijitmondal.together.core.dto.Conversation;
import com.avijitmondal.together.core.dto.ResponseDTO;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.core.service.IService;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

/**
 * @author avijit
 *
 */
public interface IConversationService extends IService {

	/**
	 * @param pageable
	 * @param userId
	 * @return
	 * @throws TogetherException
	 */
	ResponseDTO<List<Conversation>> findByUserId(Pageable pageable, String userId) throws TogetherException;

	/**
	 * @param conversationId
	 * @return
	 * @throws TogetherException
	 */
	Optional<Conversation> findById(String conversationId) throws TogetherException;
}
