package com.college.studentmanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.college.studentmanagement.dto.UserDto;
import com.college.studentmanagement.entity.User;
import com.college.studentmanagement.service.UserService;

@RestController
@RequestMapping(path="/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path="/list")
	public List<User> fetchAllUsers(){
		return userService.fetchAllUsers();
	}

	@PostMapping(path="/add")
	public User addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

}
