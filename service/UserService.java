package com.example.shiksha.service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shiksha.model.User;
import com.example.shiksha.reposiotory.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	
	
	
	public User registerUser(User user) {
//		user.setPassword(user.getPassword());
		return userRepository.save(user);
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public User update(User user) {
		Optional<User> existingUserOptional = userRepository.findById(user.getId());
		if(existingUserOptional.isPresent()) {
			User existingUser = existingUserOptional.get();
			
			existingUser.setName(user.getName());
			existingUser.setRole(user.getRole());
			existingUser.setEmailid(user.getEmailid());
			existingUser.setPassword(user.getPassword());
			return userRepository.save(existingUser);
			
		}
		else {
			throw new RuntimeException("User not found wirth id"+user.getId());
		}
	}
		public void DeleteUser(Long id) {
			if(userRepository.existsById(id)) {
				userRepository.deleteById(id);
		}
			else {
				throw new RuntimeException("User not found with id:"+ id);
			}
	}
	
//	public Optional<User> getUserByEmail(String email){
//		return userRepository.findByEmail(email);
//	}
}
