/** 
 * @class User 
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

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/*
 * la classe User représente les utilisateurs de App Avocat pour un cabinet. Ainsi nous pouvions avoir l'administrateur,
 * le(s) client(s), le(s) avocats(s), le(s) assistant(s) ou toute personne ayant des droits d'utilisation.
 */

import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import com.appavoc.model.Role;
import com.appavoc.model.enums.RegistrationStatus;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
	 public static final String STATUS_PENDING = "PENDING";
	 public static final String STATUS_VERIFIED = "VERIFIED";
	
	@Id
	private String id = UUID.randomUUID().toString();

	private String username;

	private String firstName;

	private String lastName;

	private String email;

	private String name;
	
	private DateTime expiryCode;
	
	private String code;
	
	private String status;
	 
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public DateTime getConfirmedDateTime() {
		return confirmedDateTime;
	}

	public void setConfirmedDateTime(DateTime confirmedDateTime) {
		this.confirmedDateTime = confirmedDateTime;
	}

	private DateTime confirmedDateTime;
	
	public User() {
	}

	public User(String name, String email, String password, String firstName, String lastName, String username, Role role) {
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.name = name;
		this.roles = new ArrayList<>();
		this.roles.add(role);
		this.status = STATUS_PENDING;
      
        
	}

	
	
	public DateTime getExpiryCode() {
		return expiryCode;
	}

	public void setExpiryCode(DateTime expiryCode) {
		this.expiryCode = expiryCode;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	private RegistrationStatus registrationStatus;
	
	private Boolean isActive;

	
	private String password;
	
	private String confirmPassword;

	private List<Role> roles;
	
	
	private Person person;

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	  /**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public List<Role> getRoles() { return roles; }
	  
	  public void setRoles(List<Role> roles) { this.roles = roles; }
	 

	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}


}
