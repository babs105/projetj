/** 
 * @class MobilePayment
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
 * la classe MobilePayment permet de payer un abonnement ou une facture grace à son operateur telephonique comme orange money
 * ,e-money et tigo cash.
 */

package com.appavoc.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MobilePayment extends Payment {

	@NotNull
	private String nationalIdentificationNumber;
	
	@NotNull
	@Size(min = 5, max = 50)
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNationalIdentificationNumber() {
		return nationalIdentificationNumber;
	}

	public void setNationalIdentificationNumber(String nationalIdentificationNumber) {
		this.nationalIdentificationNumber = nationalIdentificationNumber;
	}

}
