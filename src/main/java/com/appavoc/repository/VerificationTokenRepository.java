package com.appavoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.appavoc.model.VerificationToken;

public interface VerificationTokenRepository extends MongoRepository<VerificationToken, String> {
	
	List<VerificationToken> findByUserEmail(String email);
    List<VerificationToken> findByToken(String token);

}
