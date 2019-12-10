/** 
 * @class Administrator 
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
 * la classe Administrator représente l'administrateur de l'application. Elle est une sous classe de la classe Person
 * car l'administrateur est avant tout une personne.
 */

@Document
//public class Administrator extends Person {
public class Administrator {

	@NotNull
	private long adminNumber;
	
	@NotNull
	private long officeNumber;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getAdminNumber() {
		return adminNumber;
	}

	public void setAdminNumber(long adminNumber) {
		this.adminNumber = adminNumber;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

}
   