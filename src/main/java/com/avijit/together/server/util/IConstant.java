/*****************************************************************************
 * FILE NAME   : IConstant.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.util;

/**
 * @author avijit
 *
 */
public interface IConstant {
	String FILES_LOCATION = "C://together//db//files//";

	String TEMPORARY_FILES_LOCATION = "C://together//db//files//temporary//";
	
	String FILE_URI = "/%s/files?file_name=%s&s_id=%s";
}
