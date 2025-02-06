package com.example.shiksha.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.shiksha.model.User;
import com.example.shiksha.service.UserService;

@RestController
@RequestMapping("")
public class UserController {
	@Autowired
	private UserService userService;
	
	@GetMapping("/all")
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/register")
	public User
	registerUser(@RequestBody User user) {
		return userService.registerUser(user);
	}
	
	@PutMapping("/update")
	public User update(@RequestBody User user){
		return userService.update(user);
	}
	
	@DeleteMapping("/del")
	public ResponseEntity<String> deleteUser(@RequestParam Long id){
		userService.DeleteUser(id);
		return ResponseEntity.ok("User deleted successfully");
	}
	
}
