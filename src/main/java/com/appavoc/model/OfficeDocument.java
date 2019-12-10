/** 
 * @class Document 
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
 * la classe Document permet d'instancier un document qui sera scanné et classé en dossier.
 *  Elle entre dans la gestion des dossiers
 */

import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OfficeDocument {

	@Id
	private String id = UUID.randomUUID().toString();

	//@NotNull
	private long docNumber;

	//@NotNull
	private long folderNumber;
	
	private long legalDirectoryNumber;

	//@NotNull
	@Size(min = 1, max = 50)
	private String name;

	//@NotNull
	private byte[] document;

	private boolean important;
	
	@FutureOrPresent
	private Date date;

	private String type;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getDocNumber() {
		return docNumber;
	}

	public void setDocNumber(long docNumber) {
		this.docNumber = docNumber;
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

	public byte[] getDocument() {
		return document;
	}

	public void setDocument(byte[] document) {
		this.document = document;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public long getFolderNumber() {
		return folderNumber;
	}

	public void setFolderNumber(long folderNumber) {
		this.folderNumber = folderNumber;
	}

	public long getLegalDirectoryNumber() {
		return legalDirectoryNumber;
	}

	public void setLegalDirectoryNumber(long legalDirectoryNumber) {
		this.legalDirectoryNumber = legalDirectoryNumber;
	}

	/**
	 * @return the important
	 */
	public boolean isImportant() {
		return important;
	}

	/**
	 * @param important the important to set
	 */
	public void setImportant(boolean important) {
		this.important = important;
	}

}
