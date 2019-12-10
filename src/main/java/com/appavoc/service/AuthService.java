/** 
 * @interface AuthService 
 */

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

package com.appavoc.service;

import org.joda.time.DateTime;

import com.appavoc.model.User;

public interface AuthService {

	String getNewLoginCookieContentsForUser(User user, boolean rememberMe) throws Exception;
	String getNewEmailTokenForUser(User user, DateTime expiryCode, String code) throws Exception;
	boolean isSessionValid(String cookieContents);

	User getUserForSession(String cookieContents) throws Exception;

}
