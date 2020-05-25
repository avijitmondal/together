package com.avijitmondal.together.alive.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController(value = "/alive")
public class AliveController {
    @Autowired
    private HttpSession session;

    @ResponseBody
    @GetMapping(value = "/session_id")
    public String sessionId() {
        return this.session.getId();
    }
}
