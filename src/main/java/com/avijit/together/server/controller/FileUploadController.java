/*****************************************************************************
 * FILE NAME   : FileUploadController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.avijit.together.server.dto.FileUploadDownloadDTO;
import com.avijit.together.server.exception.UUIDConversationException;
import com.avijit.together.server.service.ConversationService;
import com.avijit.together.server.service.FileNameService;
import com.avijit.together.server.util.FileNameUtility;
import com.avijit.together.server.util.IConstant;

/**
 * @author avijit
 *
 */
@CrossOrigin
@RestController
@RequestMapping(value = "/api/{conversation_id}/files")
public class FileUploadController {

	/**
	 * 
	 */
	@Autowired
	private ConversationService conversationService;

	/**
	 * 
	 */
	@Autowired
	private FileNameService fileNameService;

	/**
	 * @param conversationId
	 * @param uploadfile
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public HttpEntity<FileUploadDownloadDTO> uploadSingleFile(HttpServletResponse response,
			@PathVariable("conversation_id") String conversationId, @RequestParam("file") MultipartFile uploadfile) {

		try {
			if (!conversationService.isExists(conversationId)) {
				return new ResponseEntity<>(new FileUploadDownloadDTO("Conversation does not exists"),
						HttpStatus.NOT_FOUND);
			}
		} catch (UUIDConversationException uuidConversationException) {
			return new ResponseEntity<>(new FileUploadDownloadDTO("Not a valid conversation ID"),
					HttpStatus.BAD_REQUEST);
		}

		if (uploadfile.isEmpty()) {
			return new ResponseEntity<>(new FileUploadDownloadDTO("please select a file!"), HttpStatus.BAD_REQUEST);
		}

		String convertedFileName = FileNameUtility.convertFileName(conversationId);

		try {
			
			saveUploadedFiles(Arrays.asList(uploadfile), convertedFileName);
			fileNameService.save(uploadfile.getOriginalFilename(), convertedFileName);

		} catch (IOException ioException) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

		response.setHeader("Location", String.format(IConstant.FILE_URI, conversationId,
				uploadfile.getOriginalFilename(), FileNameUtility.getSessionId(convertedFileName)));
		return new ResponseEntity<>(
				new FileUploadDownloadDTO("Successfully uploaded - " + uploadfile.getOriginalFilename()),
				HttpStatus.OK);
	}

	/**
	 * @param conversationId
	 * @param extraField
	 * @param uploadfiles
	 * @return
	 *//*
	@RequestMapping(value = "/multiple", method = RequestMethod.POST)
	public HttpEntity<FileUploadDownloadDTO> uploadMultipleFile(@PathVariable("conversation_id") String conversationId,
			@RequestParam("extraField") String extraField, @RequestParam("files") MultipartFile[] uploadfiles) {

		try {
			if (!conversationService.isExists(conversationId)) {
				return new ResponseEntity<>(new FileUploadDownloadDTO("Conversation does not exists"),
						HttpStatus.NOT_FOUND);
			}
		} catch (UUIDConversationException uuidConversationException) {
			return new ResponseEntity<>(new FileUploadDownloadDTO("Not a valid conversation ID"),
					HttpStatus.BAD_REQUEST);
		}

		String uploadedFileName = Arrays.stream(uploadfiles).map(x -> x.getOriginalFilename())
				.filter(x -> !StringUtils.isEmpty(x)).collect(Collectors.joining(" , "));

		if (StringUtils.isEmpty(uploadedFileName)) {
			FileUploadDownloadDTO fileUploadDownloadDTO = new FileUploadDownloadDTO("please select a file!");
			return new ResponseEntity<>(fileUploadDownloadDTO, HttpStatus.BAD_REQUEST);
		}

		try {

			saveUploadedFiles(Arrays.asList(uploadfiles), conversationId);

		} catch (IOException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		FileUploadDownloadDTO fileUploadDownloadDTO = new FileUploadDownloadDTO(
				"Successfully uploaded - " + uploadedFileName);
		return new ResponseEntity<>(fileUploadDownloadDTO, HttpStatus.OK);

	}*/

	/**
	 * save file
	 * 
	 * @param files
	 * @throws IOException
	 */
	private void saveUploadedFiles(List<MultipartFile> files, String convertedFileName) throws IOException {

		for (MultipartFile file : files) {

			if (file.isEmpty()) {
				continue; // next pls
			}

			byte[] bytes = file.getBytes();
			Path path = Paths.get(IConstant.FILES_LOCATION + convertedFileName);
			Files.write(path, bytes);

		}

	}
}
