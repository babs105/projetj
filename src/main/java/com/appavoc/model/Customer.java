/** 
 * @class Customer 
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

import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

/*
 * la classe Customer permet d'instancier un client avec tous ses informations. Elle est une sous classe de Person car un client 
 * est aussi une personne.
 */

@Document
//public class Customer extends Person {
public class Customer {

	//@NotNull
	private long customerNumber;
	
	//@NotNull
	private long officeNumber;
	

	@Size(min = 0, max = 100)
	private String type;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
 