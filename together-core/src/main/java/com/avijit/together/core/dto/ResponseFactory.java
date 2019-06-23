/*****************************************************************************
 * FILE NAME   : ResponseFactory.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 7, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.core.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.avijit.together.core.exception.ErrorCode;
import com.avijit.together.core.exception.TogetherException;

/**
 * @author avijit
 */
public class ResponseFactory {

    private ResponseFactory() {
    }

    /**
     * @param status
     * @return
     */
    public static HttpEntity<?> getResponse(HttpStatus status) {
        ResponseDTO responseDTO = new ResponseDTO(status);
        responseDTO.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(responseDTO, status);
    }

    /**
     * @param status
     * @param errorCode
     * @param errorDetails
     * @param message
     * @param path
     * @return
     */
    public static HttpEntity<?> getResponse(HttpStatus status, ErrorCode errorCode, String errorDetails, String message,
                                            String path) {
        ResponseDTO responseDTO = new ResponseDTO(status);
        responseDTO.setTimestamp(LocalDateTime.now().toString());
        responseDTO.setErrorCode(getErrorString(errorCode.name(), errorCode.getCode()));
        responseDTO.setErrorDetails(errorDetails);
        responseDTO.setMessage(message);
        responseDTO.setPath(path);
        return new ResponseEntity<>(responseDTO, status);
    }

    /**
     * @param status
     * @param togetherException
     * @param message
     * @param path
     * @return
     */
    public static HttpEntity<?> getResponse(HttpStatus status, TogetherException togetherException, String message,
                                            String path) {
        ResponseDTO responseDTO = new ResponseDTO(status);
        responseDTO.setTimestamp(LocalDateTime.now().toString());
        responseDTO.setErrorCode(
                getErrorString(togetherException.getErrorCode().name(), togetherException.getErrorCode().getCode()));
        responseDTO.setErrorDetails(togetherException.getErrorDetails());
        responseDTO.setMessage(message);
        responseDTO.setPath(path);
        return new ResponseEntity<>(responseDTO, status);
    }

    /**
     * @param errorName
     * @param errorCode
     * @return
     */
    private static String getErrorString(String errorName, String errorCode) {
        return errorName + "(" + errorCode + ")";
    }
}
