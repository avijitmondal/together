/*****************************************************************************
 * FILE NAME   : ResponseFactory.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Nov 7, 2017
 * DESCRIPTION : together
 ****************************************************************************/
package com.avijitmondal.together.core.dto;

import java.time.LocalDateTime;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.TogetherException;

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
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(status);
        errorResponseDTO.setTimestamp(LocalDateTime.now().toString());
        return new ResponseEntity<>(errorResponseDTO, status);
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
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(status);
        errorResponseDTO.setTimestamp(LocalDateTime.now().toString());
        errorResponseDTO.setErrorCode(getErrorString(errorCode.name(), errorCode.getCode()));
        errorResponseDTO.setErrorDetails(errorDetails);
        errorResponseDTO.setMessage(message);
        errorResponseDTO.setPath(path);
        return new ResponseEntity<>(errorResponseDTO, status);
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
        ErrorResponseDTO errorResponseDTO = new ErrorResponseDTO(status);
        errorResponseDTO.setTimestamp(LocalDateTime.now().toString());
        errorResponseDTO.setErrorCode(
                getErrorString(togetherException.getErrorCode().name(), togetherException.getErrorCode().getCode()));
        errorResponseDTO.setErrorDetails(togetherException.getErrorDetails());
        errorResponseDTO.setMessage(message);
        errorResponseDTO.setPath(path);
        return new ResponseEntity<>(errorResponseDTO, status);
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
