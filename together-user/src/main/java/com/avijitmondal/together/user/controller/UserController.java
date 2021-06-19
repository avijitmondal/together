/*****************************************************************************
 * FILE NAME   : UserController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijitmondal.together.user.controller;

import com.avijitmondal.together.core.data.Constants;
import com.avijitmondal.together.core.dto.ResponseDTO;
import com.avijitmondal.together.core.dto.ResponseFactory;
import com.avijitmondal.together.core.dto.User;
import com.avijitmondal.together.core.exception.ErrorCode;
import com.avijitmondal.together.core.exception.IErrorDetails;
import com.avijitmondal.together.core.exception.TogetherException;
import com.avijitmondal.together.user.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = Constants.API_USERS)
public class UserController {

    private final Log logger = LogFactory.getLog(this.getClass());

    /**
     *
     */
    @Autowired
    private UserService userService;

    /**
     * @param pageable
     * @return
     */
    @GetMapping(produces = {"application/json"})
    public ResponseDTO<List<User>> findAll(HttpServletRequest request, Pageable pageable) {
        return userService.findAll(pageable);
    }

    @GetMapping(value = "/{user_id}", produces = {"application/json"})
    public HttpEntity<?> findById(HttpServletRequest request, @PathVariable("user_id") String userId) {
        try {
            logger.debug("finding user with ID: " + userId);
            Optional<User> user = userService.findById(userId);
            if (user.isPresent()) {
                return new ResponseEntity<>(user.get(), HttpStatus.OK);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND,
                    String.format(IErrorDetails.USER_ID_NOT_FOUND, userId), IErrorDetails.ENTER_VALID_USER_ID,
                    request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.ENTER_VALID_USER_ID, request.getRequestURI());
        }
    }

    @PostMapping(consumes = {"application/json"}, produces = {"application/json"})
    public HttpEntity<?> save(HttpServletRequest request, @Valid @RequestBody User user, BindingResult bindingResult,
                              SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                logger.error(fieldError);
            }
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, ErrorCode.USER_NOT_ADDED,
                    IErrorDetails.UNABLE_TO_ADD_USER, IErrorDetails.ENTER_VALID_DATA, request.getRequestURI());
        }
        try {
            User temp = userService.save(user);
            if (null != user) {
                return new ResponseEntity<>(temp, HttpStatus.CREATED);
            }
            return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.USER_NOT_ADDED,
                    IErrorDetails.UNABLE_TO_ADD_USER, IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, togetherException,
                    IErrorDetails.TRY_SOMETIME_LATER, request.getRequestURI());
        }
    }

    /**
     * @param request
     * @param userId
     * @return
     */
    @DeleteMapping(value = "/{user_id}", produces = {"application/json"})
    public HttpEntity<?> delete(HttpServletRequest request, @PathVariable("user_id") String userId) {
        try {
            boolean result = userService.delete(userId);
            if (result) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND,
                    String.format(IErrorDetails.USER_ID_NOT_FOUND, userId), IErrorDetails.ENTER_VALID_USER_ID,
                    request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.ENTER_VALID_USER_ID, request.getRequestURI());
        }
    }
}
