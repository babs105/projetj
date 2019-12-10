/**
 * *
o	 * Copyright (c) 2019 KhydmaShore Solutions Inc.
o	 *
o	 * All rights reserved.
o	 *
o	 *****************************************************************************
 */

/**
 * @author ZEYNAB
 *
 */

package com.appavoc.controller;

import java.nio.file.AccessDeniedException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.controller.command.LoginCommand;
import com.appavoc.model.User;
import com.appavoc.service.AuthService;
import com.appavoc.service.EncryptionService;
import com.appavoc.service.UserService;
import com.appavoc.service.impl.SendingMailService;
import com.appavoc.service.impl.VerificationTokenService;
import com.appavoc.viewmodel.LoginUser;
import com.mongodb.annotations.Beta;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class AuthController {

	@Autowired
	private UserService userService;
	

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private AuthService authService;
	
	@Autowired 
	private SendingMailService sendingMailService;
	
	@Autowired
	private VerificationTokenService verificationTokenService;

	@RequestMapping(value = "/authenticate", method = {RequestMethod.OPTIONS, RequestMethod.POST}, produces = "application/json")
	@ResponseBody
	@Beta
	@Scope("session")
	public LoginUser login(@RequestBody LoginCommand loginCommand) throws Exception {
		User user = userService.getUserByUsername(loginCommand.getUsername());
		if (user == null) {
			throw new AccessDeniedException("email_password_didnot_match");
		}
		if (encryptionService.check(loginCommand.getPassword(), user.getPassword())) {
			String cookieContents = authService.getNewLoginCookieContentsForUser(user, loginCommand.isRememberMe());
			return new LoginUser(user, cookieContents);
		} else {
			throw new AccessDeniedException("email_password_didnot_match");
		}
	}
	
	
	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public User create(@RequestBody User user) throws Exception {	
		
		return userService.createUser(user);
	}

	@RequestMapping(value = "/loggedInUser", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public LoginUser loggedInUser(@RequestHeader("x-authenticate-user") String authHeader) throws Exception {
		User user = authService.getUserForSession(authHeader);
		return new LoginUser(user, authHeader);
	}
	
	
	
	@RequestMapping(value = "/emaile", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public void emaile() throws Exception {
		sendingMailService.sendVerificationMail("serigne.bass@gmail.com","54264cdf");
	}
	
	@GetMapping("/verify-email")
    @ResponseBody
    public String verifyEmail(String code) throws Exception {
        return verificationTokenService.verifyEmail(code).getBody();
    }
	
	
	

}
