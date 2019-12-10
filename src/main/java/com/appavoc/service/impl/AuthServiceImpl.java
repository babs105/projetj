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

package com.appavoc.service.impl;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;

import org.bouncycastle.jcajce.provider.digest.SHA3;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appavoc.model.CookieValue;
import com.appavoc.model.User;
import com.appavoc.service.AuthService;
import com.appavoc.service.EncryptionService;
import com.appavoc.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;


@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	private EncryptionService encryptionService;

	@Autowired
	private UserService userService;

	private SHA3.DigestSHA3 digestSHA3 = new SHA3.Digest512();
	private ObjectMapper mapper = new ObjectMapper();


	public AuthServiceImpl() throws Exception {
		mapper.registerModule(new JodaModule());
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		
		
	}

	@Override
	public String getNewLoginCookieContentsForUser(User user, boolean rememberMe) throws Exception {
		DateTime expiry = DateTime.now();
		String token = getTokenFor(user.getId(), expiry, rememberMe);
		CookieValue cookie = new CookieValue(user.getId(), expiry, token, rememberMe);
		String cookieValue = mapper.writer().writeValueAsString(cookie);
		String encryptedCookie = encryptionService.encrypt(cookieValue);
		return URLEncoder.encode(encryptedCookie, EncryptionServiceImpl.UTF_8);
	}

	@Override
	public boolean isSessionValid(String cookieContents) {
		return true;
	}

	@Override
	public User getUserForSession(String cookieContents) throws Exception {
		cookieContents = URLDecoder.decode(cookieContents, EncryptionServiceImpl.UTF_8);
		String decryptedCookie = encryptionService.decrypt(cookieContents);
		CookieValue cookieValue = mapper.readValue(decryptedCookie, CookieValue.class);
		return userService.getUserById(cookieValue.getUserId());
	}

	private String getTokenFor(String id, DateTime expiry, boolean rememberMe) {
		String contents = "userId" + id + "expiry" + expiry.toString() + "rememberMe" + rememberMe;
		byte[] digest = digestSHA3.digest(contents.getBytes());
		return new String(digest);
	}
	
	@Override
	public String getNewEmailTokenForUser(User user, DateTime expiryCode, String code) throws Exception {
		String contents = "userId" + user.getId() + "expiry" + expiryCode.toString() + "code" + code;
		byte[] digest = digestSHA3.digest(contents.getBytes());
		String token = new String(digest);
		CookieValue cookie = new CookieValue(user.getId(), expiryCode, token, false);
		String cookieValue = mapper.writer().writeValueAsString(cookie);
		String encryptedCookie = encryptionService.encrypt(cookieValue);
		return URLEncoder.encode(encryptedCookie, EncryptionServiceImpl.UTF_8);
	}

}
