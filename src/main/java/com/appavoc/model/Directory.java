/** 
 * @class Directory 
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
 * la classe Directory représente un tiroir ou l'on met tous les dossiers d'un client.
 *  Elle est constitué d'un ensemble de dossieret des informations pour la differenciation des tiroirs
 */
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document
public class Directory {

	@Id
	private String id = UUID.randomUUID().toString();

	private long directoryNumber;

	private long customerNumber;

	//@NotNull
	//@FutureOrPresent
	private Date date;
	
	@NotNull
	@Size(min = 0, max = 100)
	private String name;

	private List<Folder> folders;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getDirectoryNumber() {
		return directoryNumber;
	}

	public void setDirectoryNumber(long directoryNumber) {
		this.directoryNumber = directoryNumber;
	}

	public long getCustomerNumber() {
		return customerNumber;
	}

	public void setCustomerNumber(long customerNumber) {
		this.customerNumber = customerNumber;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<Folder> getFolders() {
		return folders;
	}

	public void setFolders(List<Folder> folders) {
		this.folders = folders;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
