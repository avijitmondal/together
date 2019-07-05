/*****************************************************************************
 * FILE NAME   : FileName.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 18, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.database.dao;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author avijit
 *
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
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
}
