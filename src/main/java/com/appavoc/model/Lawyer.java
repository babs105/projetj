/** 
 * @class Lawyer 
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

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * la classe Lawyer représente un avocat appartenant à un cabinet.
 */

@Document
//public class Lawyer extends Person {
	public class Lawyer {

	@NotNull
	private long lawyerNumber;
	
	//@NotNull
	private long officeNumber;

	private String type;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public long getLawyerNumber() {
		return lawyerNumber;
	}

	public void setLawyerNumber(long lawyerNumber) {
		this.lawyerNumber = lawyerNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

}
