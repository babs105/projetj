/** 
 * @class GiftCardPayment
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

/*
 * la classe GiftCardPayment permet de payer un abonnement ou une facture grace à son carte cadeau.
 */

package com.appavoc.model;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class GiftCardPayment extends Payment {

	@NotNull
	private String numCard;
	
	@NotNull
	private Date dateOfExpiration; 
	
	@NotNull
	private Integer pinNumber;

	public String getNumCard() {
		return numCard;
	}

	public void setNumCard(String numCard) {
		this.numCard = numCard;
	}

	public Date getDateOfExpiration() {
		return dateOfExpiration;
	}

	public void setDateOfExpiration(Date dateOfExpiration) {
		this.dateOfExpiration = dateOfExpiration;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}
	
}
