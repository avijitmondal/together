/*****************************************************************************
 * FILE NAME   : UserController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.server.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import com.avijit.together.server.dto.ResponseFactory;
import com.avijit.together.server.exception.ErrorCode;
import com.avijit.together.server.exception.IErrorDetails;
import com.avijit.together.server.exception.TogetherException;
import com.avijit.together.server.model.User;
import com.avijit.together.server.service.UserService;
import com.avijit.together.server.util.PageResource;

import static com.avijit.together.server.data.I_Constant.API_USERS;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = API_USERS)
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
    @GetMapping(produces = {"application/json"}, consumes = {"application/json"})
    public PageResource<User> list(Pageable pageable) {

        Page<User> users = userService.getAll(pageable);
        return new PageResource<>(users, "page", "size");

    }

    /**
     * @param request
     * @param email
     * @return
     */
    @GetMapping(params = "email", produces = {"application/json"}, consumes = {"application/json"})
    public HttpEntity<?> findByEmail(HttpServletRequest request, @RequestParam("email") String email) {
        try {
            User user = userService.findByEmail(email);
            if (null != user) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND,
                    String.format(IErrorDetails.USER_EMAIL_NOT_FOUND, email), IErrorDetails.ENTER_VALID_USER_EMAIL,
                    request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.ENTER_VALID_USER_EMAIL, request.getRequestURI());
        }
    }

    /**
     * @param request
     * @param userId
     * @return
     */
    @GetMapping(value = "/{user_id}", produces = {"application/json"}, consumes = {"application/json"})
    public HttpEntity<?> findById(HttpServletRequest request, @PathVariable("user_id") String userId) {
        try {
            User user = userService.findById(userId);
            if (null != user) {
                return new ResponseEntity<>(user, HttpStatus.OK);
            }
            return ResponseFactory.getResponse(HttpStatus.NOT_FOUND, ErrorCode.USER_NOT_FOUND,
                    String.format(IErrorDetails.USER_ID_NOT_FOUND, userId), IErrorDetails.ENTER_VALID_USER_ID,
                    request.getRequestURI());
        } catch (TogetherException togetherException) {
            return ResponseFactory.getResponse(HttpStatus.BAD_REQUEST, togetherException,
                    IErrorDetails.ENTER_VALID_USER_ID, request.getRequestURI());
        }

    }

    /**
     * @param request
     * @param user
     * @return
     */
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
    @DeleteMapping(value = "/{user_id}", produces = {"application/json"}, consumes = {"application/json"})
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
