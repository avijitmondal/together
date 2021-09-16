package com.avijitmondal.together.auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/unsecured")
public class UnsecuredController {

	@GetMapping
	public String unsecuredResource() {
		return "This resource is not secured";
	}
	
}
