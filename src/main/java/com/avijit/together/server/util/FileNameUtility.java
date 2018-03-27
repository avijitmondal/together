/*****************************************************************************
 * FILE NAME   : FileNameUtility.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 18, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalTime;
import java.util.UUID;

import com.avijit.together.server.data.I_Constant;

/**
 * @author avijit
 *
 */
public class FileNameUtility {

	/**
	 * @param conversationId
	 * @return
	 */
	public static String convertFileName(String conversationId) {
		String sessionId = UUID.randomUUID().toString().replaceAll("-", "");
		String timeId = LocalTime.now().toString().replaceAll(":", "");
		String convertedFileName = conversationId + "_" + sessionId + "_" + timeId;
		return convertedFileName;
	}

	/**
	 * @param conversationId
	 * @param sessionId
	 * @param timeId
	 * @return
	 */
	public static String convertFileName(String conversationId, String sessionId, String timeId) {
		return conversationId + "_" + sessionId + "_" + timeId;
	}
	
	/**
	 * @param convertedFileName
	 * @return
	 */
	public static String getSessionId(String convertedFileName) {
		return convertedFileName.substring(37, 69);
	}
	
	/**
	 * @param convertedFileName
	 * @return
	 */
	public static String getTimeId(String convertedFileName) {
		return convertedFileName.substring(70);
	}

	/**
	 * @param fileName
	 * @return
	 */
	public String getFileExtension(String fileName) {
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			return fileName.substring(fileName.lastIndexOf(".") + 1);
		} else {
			return null;
		}
	}

	/**
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public static File getSourceFile(String fileName) throws FileNotFoundException {
		File file = new File(I_Constant.FILES_LOCATION + fileName);
		if (!file.exists()) {
			throw new FileNotFoundException("file not found.");
		}
		return file;
	}

	/**
	 * @param sourceFile
	 * @param fileName
	 * @return
	 * @throws IOException
	 */
	public static File getDownloadableFile(File sourceFile, String fileName) throws IOException {
		File tempFile = new File(I_Constant.TEMPORARY_FILES_LOCATION + fileName);
		if(null != tempFile && tempFile.exists()) {
			tempFile.delete();
		}
		Files.copy(sourceFile.toPath(), tempFile.toPath());
		return tempFile;
	}
	
}
