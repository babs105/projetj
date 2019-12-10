/** 
 * @class Payment
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
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


/*
 * la classe Payment permet de gérer le paiement des abonnements au different pack. 
 */

@Document
public class Payment {

	@Id
	private String id = UUID.randomUUID().toString();

	@NotNull
	private long packNumber;

	@NotNull
	private long transactionId;

	@NotNull
	private Date transactionDate;
	
	private String userId;

	@PositiveOrZero
	@NotNull
	private Double transactionAmount;

	@NotNull
	private boolean transactionSuccess;

	@Email
	private String email;

	@NotNull
	private Address billingAddress;

	private long phone;

	@Size(min = 1, max = 30)
	private String firstName;

	@Size(min = 1, max = 30)
	private String lastName;
	
	@NotNull
	@Size(min = 1, max = 50)
	private String name;

	@NotNull
	private boolean paye;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getPackNumber() {
		return packNumber;
	}

	public void setPackNumber(long packNumber) {
		this.packNumber = packNumber;
	}

	public long getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}

	@NotNull
	@FutureOrPresent
	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(double transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public boolean isTransactionSuccess() {
		return transactionSuccess;
	}

	public void setTransactionSuccess(boolean transactionSuccess) {
		this.transactionSuccess = transactionSuccess;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isPaye() {
		return paye;
	}

	public void setPaye(boolean paye) {
		this.paye = paye;
	}

}
