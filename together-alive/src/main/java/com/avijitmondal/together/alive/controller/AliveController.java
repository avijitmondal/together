package com.avijitmondal.together.alive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class AliveController {
    @Autowired
    private HttpSession session;

    @GetMapping(value = "/session_id")
    public ResponseEntity<String> sessionId() {
        return new ResponseEntity<>(session.getId(), HttpStatus.OK);
    }
}
