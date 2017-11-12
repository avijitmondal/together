/*****************************************************************************
 * FILE NAME   : IFileNameService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 2, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.service;

import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.FileName;

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
	FileName findById(String storedFileName) throws TogetherException;
}
