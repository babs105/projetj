/** 
 * @class Subscription
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

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/*
 * la classe Subscription gére les informations sur les abonnements à un pack avec toutes les informations requis.
 */

@Document
public class Subscription {

	@Id
	private String idSubscription = UUID.randomUUID().toString();

	@NotNull
	private Long subscriptionNumber;

	@NotNull
	private Long officeNumber;

	@NotNull
	private Date date;

	@NotNull
	private Integer duration;

	@NotNull
	@Size(min = 1, max = 30)
	private String packType; 

	private long promotionNumber;

	@NotNull
	private Long transactionNumber;

	public String getIdSubscription() {
		return idSubscription;
	}

	public void setIdSubscription(String idSubscription) {
		this.idSubscription = idSubscription;
	}

	public long getSubscriptionNumber() {
		return subscriptionNumber;
	}

	public void setSubscriptionNumber(long subscriptionNumber) {
		this.subscriptionNumber = subscriptionNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

	public String getPackType() {
		return packType;
	}

	public void setPackType(String packType) {
		this.packType = packType;
	}

	public long getPromotionNumber() {
		return promotionNumber;
	}

	public void setPromotionNumber(long promotionNumber) {
		this.promotionNumber = promotionNumber;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

} 
