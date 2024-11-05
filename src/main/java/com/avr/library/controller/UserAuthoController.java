package com.avr.library.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.avr.library.dto.UserAuthRequest;
import com.avr.library.entity.User;
import com.avr.library.service.UserService;
import com.avr.library.util.JwtService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("api/user")
public class UserAuthoController {

	@Autowired
	private JwtService jwtService;
	@Autowired
	private AuthenticationManager authManager;
	@Autowired
	private UserService userService;


    
	@PostMapping("/usetaAthenticate")
	public String validateUser(@RequestBody UserAuthRequest authRequest) {

		System.out.println("UserAuthRequest " + authRequest);
		Authentication authentication = authManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateJwtToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("Invalid User Credentials!!!");
		}
	}

	@PostMapping("/addUser")
	public ResponseEntity<String> addNewUser(@Valid @RequestBody User userInfo) {

		return new ResponseEntity<String>(userService.addUser(userInfo), HttpStatus.CREATED);

	}

	@GetMapping("/welcome")
	public ResponseEntity<String> testUser() {

		return new ResponseEntity<String>("Welcome test Uer", HttpStatus.CREATED);
	}
}
