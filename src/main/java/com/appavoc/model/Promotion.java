/** 
 * @class Office 
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
 * la classe Promotion gére les information sur les differents reduction ou remise que l'on peut faire à un client abonné.
 */

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Promotion {

	@Id
	private String id = UUID.randomUUID().toString(); 

	@NotNull
	private long promotionNumber;
	
	private long packNumber;

	public long getPackNumber() {
		return packNumber;
	}

	public void setPackNumber(long packNumber) {
		this.packNumber = packNumber;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setTransactionNumber(Long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	@Size(min = 1, max = 50)
	private String type;

	//@NotNull
	@Size(min = 1, max = 30)
	private String name;

	//@NotNull
	//@PositiveOrZero
	private Double amount;

	//@NotNull
	//@FutureOrPresent
	private Date date;

	//@NotNull
	private int duration;

	//@NotNull
	private long transactionNumber;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getPromotionNumber() {
		return promotionNumber;
	}

	public void setPromotionNumber(long promotionNumber) {
		this.promotionNumber = promotionNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

}
