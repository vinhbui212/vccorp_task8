package com.example.authenticatingldap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsImpl;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HomeController {

	@Autowired
	private LdapTemplate ldapTemplate;

	@GetMapping("/")
	public String index(Authentication authentication) {
		String username = authentication.getName();
		List<String> results = search(username);

		if (results.isEmpty()) {
			return "Welcome to the home page, " + username + "! No CN found.";
		} else {
			return "Welcome to the home page, " + username + "! CN: " + results;
		}
	}

	public List<String> search(String username) {
		return ldapTemplate.search(
				"ou=people,dc=springframework,dc=org",
				"(cn=" + username + ")",
				(AttributesMapper<String>) attrs -> (String) attrs.get("cn").get()
		);
	}
}
