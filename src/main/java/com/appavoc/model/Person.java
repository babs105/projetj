/** 
 * @class Person 
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

/*
 * la classe Person permet d'instancier une personne avec toute les informations nécessaires.
 */

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.web.multipart.MultipartFile;

//public class Person extends User {
	public class Person {
	//@NotNull
	//@PositiveOrZero
	private int age;

	private String title;

	//@NotNull
	private String sex;

	//@NotNull
	private Location location;
	
	private String address;

	//@NotNull
	private String phone;

	//@NotNull
	private String occupation;

	private String ethnicity;

	private String maritalStatus;
	
	private Date lastConnexionTime;

	private MultipartFile image;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public MultipartFile getImage() {
		return image;
	}

	public void setImage(MultipartFile image) {
		this.image = image;
	}

	public String getLabel() {
		return title;
	}

	public void setLabel(String label) {
		this.title = label;
	}
	
	public Date getLastConnexionTime() {
		return lastConnexionTime;
	}

	public void setLastConnexionTime(Date lastConnexionTime) {
		this.lastConnexionTime = lastConnexionTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
