/**
 * Copyright (c) 2022 issac.rajan@gmail.com. All rights reserved.
 */
package com.issac.idp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.issac.idp.dto.LoginDTO;
import com.issac.idp.service.UserService;

import jakarta.validation.Valid;

/**
 * @author issac
 *
 */
@RestController()
public class AuthLocalController {

	private UserService userService;

	public AuthLocalController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping(path = "/auth/authlocal/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@Valid @RequestBody LoginDTO login) {
		String jwt = userService.validateLoginAndCreateJwt(login.getEmail(), login.getPassword());
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}

	@PostMapping(path = "/auth/test", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> logintest(@Valid @RequestBody LoginDTO login) {
		String jwt = userService.validateLoginAndCreateJwt(login.getEmail(), login.getPassword());
		return new ResponseEntity<>(jwt, HttpStatus.OK);
	}
}
