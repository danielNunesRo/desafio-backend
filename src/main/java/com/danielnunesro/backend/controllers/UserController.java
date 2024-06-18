package com.danielnunesro.backend.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.danielnunesro.backend.dtos.UserDTO;
import com.danielnunesro.backend.entities.Users;
import com.danielnunesro.backend.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Users> create(@RequestBody UserDTO userDTO) {
		Users newUser = userService.createUser(userDTO);
		return new ResponseEntity<>(newUser, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Users>> findAll() {
		List<Users> list = userService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
}
