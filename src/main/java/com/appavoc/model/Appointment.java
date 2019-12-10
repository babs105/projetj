/** 
 * @class Appointment 
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

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

/*
 * La classe Appointment permet d'avoir des informations sur les rendez-vous et tout autre
 * événements de ce type.
 */

@Document
public class Appointment {

	@Id
	private String id = UUID.randomUUID().toString();
	
	@NotNull
	private long appointmentNumber;

	private long customerNumber;

	@NotNull
	private long lawyerNumber;
	
	private String userId;


	private long folderNumber;

	@NotNull
	@Size(min = 0, max = 100)
	private String name;

	@Size(min = 0, max = 500)
	private String type;

	@NotNull
	@FutureOrPresent
	private Date appointmentDate;

	private String place;

	@NotNull
	private Integer hour;

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

	public long getAppointmentNumber() {
		return appointmentNumber;
	}

	public void setAppointmentNumber(long appointmentNumber) {
		this.appointmentNumber = appointmentNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getLieu() {
		return place;
	}

	public void setLieu(String lieu) {
		this.place = lieu;
	}

	public long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public long getFolderNumber() {
		return folderNumber;
	}

	public void setFolderNumber(long folderNumber) {
		this.folderNumber = folderNumber;
	}

	public long getLawyerNumber() {
		return lawyerNumber;
	}

	public void setLawyerNumber(long lawyerNumber) {
		this.lawyerNumber = lawyerNumber;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public int getHour() {
		return hour;
	}

	public void setHour( int hour) {
		this.hour = hour;
	}

}
