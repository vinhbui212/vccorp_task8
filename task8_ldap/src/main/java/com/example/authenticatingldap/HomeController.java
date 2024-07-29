package com.example.authenticatingldap;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {

	@GetMapping("/")
	public String index(Authentication authentication) {
		String username = authentication.getName();


		return "Welcome to the home page, " + username + "!"
				;
	}
}






