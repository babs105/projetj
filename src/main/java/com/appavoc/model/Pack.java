/**
 * @class Pack
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

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

/*
 * la classe Pack montre les caractéristiques des differents pack de AppAVocat.
 */

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Pack {
	
	@Id
	private String id = UUID.randomUUID().toString();

	@NotNull
	private long packNumber;
	
	private String type;
	
	private long lawyerNumber;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getLawyerNumber() {
		return lawyerNumber;
	}

	public void setLawyerNumber(long lawyerNumber) {
		this.lawyerNumber = lawyerNumber;
	}

	public void setNumberOfUsers(Integer numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	//@NotNull
	@Size(min = 0, max = 500)
	private String description;

	//@NotNull
	private int numberOfUsers;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	//@NotNull
	@PositiveOrZero
	private Double price;

	private double adjustedPrice;

	public long getPackNumber() {
		return packNumber;
	}

	public void setPackNumber(long packNumber) {
		this.packNumber = packNumber;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getNumberOfUsers() {
		return numberOfUsers;
	}

	public void setNumberOfUsers(int numberOfUsers) {
		this.numberOfUsers = numberOfUsers;
	}

	public double getAdjustedPrice() {
		return adjustedPrice;
	}

	public void setAdjustedPrice(double adjustedPrice) {
		this.adjustedPrice = adjustedPrice;
	}

}
