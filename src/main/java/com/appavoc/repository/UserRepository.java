package com.appavoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.appavoc.controller.command.LoginCommand;

import com.appavoc.model.User;


public interface UserRepository extends MongoRepository<User, String> {
    User findByUsername(String username);
    
	User insert(LoginCommand loginCommand);
	List<User> findByEmail(String email);
}
