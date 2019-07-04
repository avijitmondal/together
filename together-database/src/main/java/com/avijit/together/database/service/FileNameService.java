/*****************************************************************************
 * FILE NAME   : FileNameService.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 2, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.service;

import com.avijit.together.database.dao.FileName;
import com.avijit.together.database.repository.IFileNameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.IErrorDetails;
import com.avijit.together.core.exception.TogetherException;

import java.util.Optional;

/**
 * @author avijit
 *
 */
@Service("fileNameService")
public class FileNameService implements IFileNameService {

	/**
	 * 
	 */
	@Autowired
	private IFileNameRepository iFileNameRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.avijit.together.database.service.IFileNameService#save(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public FileName save(String originalFileName, String convertedFileName) throws TogetherException {
		FileName fileName = new FileName();
		fileName.setOriginalFileName(originalFileName);
		fileName.setStoredFileName(convertedFileName);
		try {
			return iFileNameRepository.save(fileName);
		} catch (Exception exception) {
			throw new TogetherException(ErrorCode.FILE_NOT_ADDED, IErrorDetails.UNABLE_TO_ADD_FILE);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.avijit.together.database.service.IFileNameService#findById(java.lang.
	 * String)
	 */
	@Override
	public Optional<FileName> findById(String storedFileName) throws TogetherException {
		return iFileNameRepository.findById(storedFileName);
	}

}
