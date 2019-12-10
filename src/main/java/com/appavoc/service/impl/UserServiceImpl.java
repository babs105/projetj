package com.appavoc.service.impl;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appavoc.model.User;
import com.appavoc.repository.UserRepository;
import com.appavoc.service.EncryptionService;
import com.appavoc.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EncryptionService encryptionService;
	
	
	@Override
	public User getUserByUsername(String username) {
		logger.info(String.format("user=%s is trying to login.", username));
		return userRepository.findByUsername(username);
	}
	
	//private String verificationCode=UUID.randomUUID().toString();
	@Override
	public User createUser(User user) {
		
		if(user.getPassword().equals(user.getConfirmPassword())){
		try {
			user.setPassword(encryptionService.getSaltedHash(user.getPassword()));	
		} catch (Exception e) {
			e.printStackTrace();
			
			}
		
		userRepository.insert(user);
	
		}
		return user;
	}
	
	
	
	@Override
	public User getUserById(String id) {
		return userRepository.findById(id).get();
	}
	
	@Override
	public void deleteUser(String id) {
		userRepository.deleteById(id);
	}
}
