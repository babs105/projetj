/** 
 * @class Bill
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
 * la classe Bill permet d'instancier une facture avec tous les informations nécessaires. 
 * Ainsi l'on pourra faire la gestion des factures des clients. 
 */

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Bill {

	@Id
	private String id = UUID.randomUUID().toString();
	
	//@NotNull
	private long billNumber;

	//@NotNull
	private long lawyerNumber;

	
	private List<Lawyer> partners;
	
	//@NotNull
	private long folderNumber;

	//@NotNull
	private long customerNumber;

	//@NotNull
	private long officeNumber;
	
	@Size(min = 0, max = 100)
	private String billMode;

	@Size(min = 0, max = 00)
	private String periodicity;

	private Date deadline;

	
	private Date billDate;

	//@NotBlank
	private String billType;

	//@NotNull
	@PositiveOrZero
	private Double billAmount;

	//@PositiveOrZero
	private double rate;
	
	@PositiveOrZero
	private double subTotal;

	//@NotNull
	@PositiveOrZero
	private Double minutePrice = 0.0;

	private long transactionNumber;

	
	public List<Lawyer> getPartners() { return partners; }
	  
    public void setPartners(List<Lawyer> partners) { this.partners = partners; }
	 

	public long getFolderNumber() {
		return folderNumber;
	}

	public void setFolderNumber(long folderNumber) {
		this.folderNumber = folderNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getBillNumber() {
		return billNumber;
	}

	public void setBillNumber(long billNumber) {
		this.billNumber = billNumber;
	}

	public String getBillMode() {
		return billMode;
	}

	public void setBillMode(String billMode) {
		this.billMode = billMode;
	}

	public String getPeriodicity() {
		return periodicity;
	}

	public void setPeriodicity(String periodicity) {
		this.periodicity = periodicity;
	}

	public Date getDeadline() {
		return deadline;
	}

	public void setDeadline(Date deadline) {
		this.deadline = deadline;
	}

	public Date getBillDate() {
		return billDate;
	}

	public void setBillDate(Date billDate) {
		this.billDate = billDate;
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public double getBillAmount() {
		return billAmount;
	}

	public void setBillAmount(double billAmount) {
		this.billAmount = billAmount;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getMinutePrice() {
		return minutePrice;
	}

	public void setMinutePrice(double minutePrice) {
		this.minutePrice = minutePrice;
	}

	public long getLawyerNumber() {
		return lawyerNumber;
	}

	public void setLawyerNumber(long lawyerNumber) {
		this.lawyerNumber = lawyerNumber;
	}

	public long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public long getTransactionNumber() {
		return transactionNumber;
	}

	public void setTransactionNumber(long transactionNumber) {
		this.transactionNumber = transactionNumber;
	}

	public long getOfficeNumber() {
		return officeNumber;
	}

	public void setOfficeNumber(long officeNumber) {
		this.officeNumber = officeNumber;
	}

	public double getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(double subTotal) {
		this.subTotal = subTotal;
	}

}
