/** 
 * @class LegalDirectory 
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

/*
 * la classe LegalDirectory permet d'avoir un annuaire juridique composé de l'ensemble des jugements des cas mineures
 * comme un accident de la route.
 */

import java.util.List;
import java.util.UUID;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class LegalDirectory {

	private String id = UUID.randomUUID().toString();
	
	@NotNull
	private long legalDirectoryNumber;

	@NotNull
	@Size(min = 0, max = 500)
	private String name;
	
	@NotNull
	@FutureOrPresent
	private Date date;

	private List<OfficeDocument> articles;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getLegalDirectoryNumber() {
		return legalDirectoryNumber;
	}

	public void setLegalDirectoryNumber(long legalDirectoryNumber) {
		this.legalDirectoryNumber = legalDirectoryNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<OfficeDocument> getArticles() {
		return articles;
	}

	public void setArticle(List<OfficeDocument> articles) {
		this.articles = articles;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
