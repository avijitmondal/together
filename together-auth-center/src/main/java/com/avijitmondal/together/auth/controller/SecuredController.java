package com.avijitmondal.together.auth.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/secured")
public class SecuredController {

	@GetMapping("/user")
	public String securedResource(Authentication auth) {
		return "This resource is secured. Authentication: " + auth.getName() + "; Authorities: " + auth.getAuthorities();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/admin")
	public String securedResourceAdmin(Authentication auth) {
		return "This resource is secured. Authentication: " + auth.getName() + "; Authorities: " + auth.getAuthorities();
	}
}
