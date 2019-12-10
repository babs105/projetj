/** 
 * @class Office 
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

import java.util.Date;

/*
 * la classe Office représente le cabinet d'avocat avec toutes les informations relatives à celui-ci.
 */

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Office {

	@Id
	private String id = UUID.randomUUID().toString();

	@NotNull
	private long officeNumber;

	@NotBlank
	@Size(min = 1, max = 50)
	private String name;

	//@NotNull
	private Address address;

	//@Email
	private String email;

	//@NotNull
	private long fax;

	//@NotNull
	private String phone;

	//@NotNull
   // @FutureOrPresent
	private Date creationDate;

	private String directorName;

	private Date lastConnexionTime;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getEmail() {
		return email;
	}

	public void setE_mail(String email) {
		this.email = email;
	}

	public long getFax() {
		return fax;
	}

	public void setFax(long fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDirectorName() {
		return directorName;
	}

	public void setDirectorName(String directorName) {
		this.directorName = directorName;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastConnexionTime() {
		return lastConnexionTime;
	}

	public void setLastConnexionTime(Date lastConnexionTime) {
		this.lastConnexionTime = lastConnexionTime;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

}
