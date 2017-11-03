/*****************************************************************************
 * FILE NAME   : ExceptionHandler.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

import com.avijit.together.server.model.bean.ErrorBean;

/**
 * @author avijit
 *
 */
@ControllerAdvice
public class ExceptionHandler {

	/**
	 * @param request
	 * @param throwable
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(MultipartException.class)
	@ResponseBody
	ResponseEntity<?> handleMultipartFileException(HttpServletRequest request, Throwable throwable) {

		HttpStatus status = getStatus(request);

		return new ResponseEntity<>(new ErrorBean("0x000123", "Attachment size exceeds the allowable limit! (10MB)"),
				status);

		// return new ResponseEntity("Attachment size exceeds the allowable
		// limit! (10MB)", status);

		// example
		// return new ResponseEntity(ex.getMessage(), status);
		// return new ResponseEntity("success", responseHeaders, HttpStatus.OK);

	}

	/**
	 * @param fileNotFoundException
	 * @param response
	 * @param request
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = FileNotFoundException.class)
	@ResponseBody
	ResponseEntity<?> handleFileNotFoundException(FileNotFoundException fileNotFoundException,
			HttpServletResponse response, HttpServletRequest request) {
		System.out.println("handling file not found exception");
		HttpStatus status = getStatus(request);

		return new ResponseEntity<>(new ErrorBean("404", fileNotFoundException.getMessage()), status);
	}

	/**
	 * @param ioException
	 * @param response
	 * @param request
	 * @return
	 */
	@org.springframework.web.bind.annotation.ExceptionHandler(value = IOException.class)
	@ResponseBody
	ResponseEntity<?> handleIOException(IOException ioException, HttpServletResponse response,
			HttpServletRequest request) {
		System.out.println("handling io exception");
		HttpStatus status = getStatus(request);

		return new ResponseEntity<>(new ErrorBean("500", ioException.getMessage()), status);
	}

	/**
	 * @param request
	 * @return
	 */
	private HttpStatus getStatus(HttpServletRequest request) {
		Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (statusCode == null) {
			return HttpStatus.INTERNAL_SERVER_ERROR;
		}
		return HttpStatus.valueOf(statusCode);
	}
}
