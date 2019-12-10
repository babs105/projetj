/** 
 * @class Assistant 
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
 * la classe Assistant représente l'assistant(e) du cabinet. Elle est une sous classe de la classe Person
 * car l'Assistant est avant tout une personne.
 */

@Document
//public class Assistant extends Person {
public class Assistant {
	@NotNull
	private long assistantNumber;
	
	@NotNull
	private long officeNumber;
	
	private User user;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getAssistantNumber() {
		return assistantNumber;
	}

	public void setAssistantNumber(long l) {
		this.assistantNumber = l;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

}
 