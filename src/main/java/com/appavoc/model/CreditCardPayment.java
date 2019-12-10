/** 
 * @class CreditCardPayment
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
 * la classe CreditCardPayment permet de payer un abonnement ou une facture grace à son carte de credit.
 */

package com.appavoc.model;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class CreditCardPayment extends Payment {

	@NotNull
	private Long cardNumber;

	@NotNull
	private Date dateExpiration;

	@NotNull
	private Integer threeLastNumber;

	@NotBlank
	private String cardName;
	
	@Size(min = 0, max = 100)
	private String type;
	
	public long getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}

	public Date getDateExpiration() {
		return dateExpiration;
	}

	public void setDateExpiration(Date dateExpiration) {
		this.dateExpiration = dateExpiration;
	}

	public int getThreeLastNumber() {
		return threeLastNumber;
	}

	public void setThreeLastNumber(int threeLastNumber) {
		this.threeLastNumber = threeLastNumber;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
}
