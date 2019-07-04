/*****************************************************************************
 * FILE NAME   : FileName.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 18, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author avijit
 *
 */
@Entity
@Table(name = "FILE_NAME")
public class FileName {
	
	/**
	 * 
	 */
	@Id
	@Column(name = "STORED_FILE_NAME")
	private String storedFileName;
	
	/**
	 * 
	 */
	@Column(name = "ORIGINAL_FILE_NAME")
	private String originalFileName;

	/**
	 * 
	 */
	public FileName() {
		super();
	}

	/**
	 * @param storedFileName
	 * @param originalFileName
	 */
	public FileName(String storedFileName, String originalFileName) {
		super();
		this.storedFileName = storedFileName;
		this.originalFileName = originalFileName;
	}

	/**
	 * @return the storedFileName
	 */
	public String getStoredFileName() {
		return storedFileName;
	}

	/**
	 * @param storedFileName
	 *            the storedFileName to set
	 */
	public void setStoredFileName(String storedFileName) {
		this.storedFileName = storedFileName;
	}

	/**
	 * @return the originalFileName
	 */
	public String getOriginalFileName() {
		return originalFileName;
	}

	/**
	 * @param originalFileName
	 *            the originalFileName to set
	 */
	public void setOriginalFileName(String originalFileName) {
		this.originalFileName = originalFileName;
	}

}
