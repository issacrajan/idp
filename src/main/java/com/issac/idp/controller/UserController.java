/**
 * 
 */
package com.issac.idp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.issac.idp.dto.UserInfoDTO;
import com.issac.idp.service.UserService;

/**
 * @author issac
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<UserInfoDTO> getUser(@PathVariable("id") String id) {
		UserInfoDTO userInfoDTO = userService.getUser(id);

		return new ResponseEntity<>(userInfoDTO, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<UserInfoDTO> saveUser(@RequestBody UserInfoDTO userInfoDTO) {
		System.out.println(userInfoDTO);
		UserInfoDTO savedDTO = userService.save(userInfoDTO);

		return new ResponseEntity<>(savedDTO, HttpStatus.OK);
	}
}
