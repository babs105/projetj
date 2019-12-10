/** 
 * @class CookieValue 
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

package com.appavoc.model;





import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;



/*
 * la classe CookieValue permet d'avoir des informations sur l'utilisateur
 */

public class CookieValue {

//private DateTimeFormatter isoDateTimeFormatter = ISODateTimeFormat.dateTime();

	@NotNull
	private String userId;

	private DateTime expiry;

	private String token;

	private boolean rememberMe;

	public CookieValue() {
	}

	public CookieValue(String userId, DateTime expiry, String token, boolean rememberMe) {
		this.userId = userId;
		this.expiry = expiry;
		this.token = token;
		this.rememberMe = rememberMe;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public DateTime getExpiry() {
		return expiry;
	}

	public void setExpiry(DateTime expiry) {
		this.expiry = expiry;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public boolean isRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

//	public DateTimeFormatter getIsoDateTimeFormatter() {
//		return isoDateTimeFormatter;
//	}
//
//	public void setIsoDateTimeFormatter(DateTimeFormatter isoDateTimeFormatter) {
//		this.isoDateTimeFormatter = isoDateTimeFormatter;
//	}
}
