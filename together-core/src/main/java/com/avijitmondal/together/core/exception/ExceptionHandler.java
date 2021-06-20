/*****************************************************************************
 * FILE NAME   : ExceptionHandler.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Oct 17, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.exception;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.avijitmondal.together.core.bean.ErrorBean;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartException;

/**
 * @author avijit
 *
 */
@ControllerAdvice
public class ExceptionHandler {
	private final Log logger = LogFactory.getLog(this.getClass());

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
		logger.error("handling file not found exception");
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
		logger.error("handling io exception");
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
