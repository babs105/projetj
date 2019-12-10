package com.appavoc.service.impl;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.appavoc.model.User;
import com.appavoc.model.VerificationToken;
import com.appavoc.repository.UserRepository;
import com.appavoc.repository.VerificationTokenRepository;
import com.appavoc.service.AuthService;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class VerificationTokenService {
	
	final Logger logger = LoggerFactory.getLogger(VerificationTokenService.class);
	
	@Autowired
	AuthService authService;
	
	
	
	/*
	 * private UserRepository userRepository; private VerificationTokenRepository
	 * verificationTokenRepository; private SendingMailService sendingMailService;
	 */
	/*
	 * @Autowired public VerificationTokenService(UserRepository userRepository,
	 * VerificationTokenRepository verificationTokenRepository, SendingMailService
	 * sendingMailService){ this.userRepository = userRepository;
	 * this.verificationTokenRepository = verificationTokenRepository;
	 * this.sendingMailService = sendingMailService; }
	 */
    
	/*
	 * public void createVerification(String email){
	 * 
	 * List<User> users = userRepository.findByEmail(email);
	 * 
	 * User user; if (users.isEmpty()) { user = new User(); user.setEmail(email);
	 * userRepository.save(user); } else { user = users.get(0); } user =
	 * users.get(0); List<VerificationToken> verificationTokens =
	 * verificationTokenRepository.findByUserEmail(email); VerificationToken
	 * verificationToken; if (verificationTokens.isEmpty()) { verificationToken =
	 * new VerificationToken(); verificationToken.setUser(user);
	 * verificationTokenRepository.save(verificationToken); } else {
	 * verificationToken = verificationTokens.get(0); }
	 * 
	 * sendingMailService.sendVerificationMail(email, verificationToken.getToken());
	 * }
	 */

    public ResponseEntity<String> verifyEmail(String token) throws Exception{
       User user = authService.getUserForSession(token);
       logger.info("USER:"+user);
       
        if(user == null) {
            return ResponseEntity.badRequest().body("Invalid token.");
        }
        if (user.getExpiryCode().isBefore(DateTime.now())) {
            return ResponseEntity.unprocessableEntity().body("Expired token.");
        }

        user.setConfirmedDateTime(DateTime.now());
        user.setStatus(VerificationToken.STATUS_VERIFIED);
        user.setIsActive(true);
     
        return ResponseEntity.ok("You have successfully verified your email address.");
    }


}
