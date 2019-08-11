/*****************************************************************************
 * FILE NAME   : UserController.java
 * VERSION     : 1.0
 * AUTHOR      : avijit
 * DATE        : Aug 23, 2017
 * DESCRIPTION : together-server
 ****************************************************************************/
package com.avijit.together.user.controller;

import com.avijit.together.core.data.I_Constant;
import com.avijit.together.core.dto.ResponseDTO;
import com.avijit.together.core.dto.User;
import com.avijit.together.user.service.UserService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author avijit
 */
@CrossOrigin
@RestController
@RequestMapping(value = I_Constant.API_USERS)
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
    public ResponseDTO<List<User>> findAll(Pageable pageable) {
        return userService.findAll(pageable);
    }
}
