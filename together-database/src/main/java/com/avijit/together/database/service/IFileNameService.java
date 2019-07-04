/*****************************************************************************
 * FILE NAME   : IFileNameService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 2, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import com.avijit.together.core.exception.TogetherException;
import com.avijit.together.database.dao.FileName;

import java.util.Optional;

/**
 * @author avijit
 *
 */
public interface IFileNameService {
	/**
	 * @param originalFileName
	 * @param convertedFileName
	 * @return
	 * @throws TogetherException
	 */
	FileName save(String originalFileName, String convertedFileName) throws TogetherException;
	
	/**
	 * @param storedFileName
	 * @return
	 * @throws TogetherException
	 */
	Optional<FileName> findById(String storedFileName) throws TogetherException;
}
