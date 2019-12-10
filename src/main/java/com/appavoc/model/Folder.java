/** 
 * @class Folder 
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
 * la classe Folder repésente le dossier d'un client.Elle est constitué d'un ensemble de documents et les informations 
 * relatives à un dossier.  
 */

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document
public class Folder {

	@Id
	private String id = UUID.randomUUID().toString();

	//@NotNull
	private long folderNumber;

	//@NotNull
	private long lawyerNumber;
	
	private String userId;
	
	@NotNull
	private long directoryNumber;

	//@NotNull
	//@FutureOrPresent
	private Date date;
	
	private boolean favorites;
	
	private boolean important;
	
	private Date workingStart;
	
	private Date workingEnd;

	@Size(min = 0, max = 100)
	private String mode;

	@NotNull
	@Size(min = 0, max = 100)
	private String name;

	@PositiveOrZero
	private double timeSpend;
	
	@Size(min = 0, max = 200)
	private String follow_up;

	@Size(min = 0, max = 200)
	private String jurisdiction;

	@Size(min = 0, max = 500)
	private String codeLagacySystem;

	private List<CaseWorker> caseWorkers ;

	private List<OfficeDocument> files;
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public long getDirectoryNumber() {
		return directoryNumber;
	}

	public void setDirectoryNumber(long directoryNumber) {
		this.directoryNumber = directoryNumber;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public double getTimeSpend() {
		return timeSpend;
	}

	public void setTimeSpend(double timeSpend) {
		this.timeSpend = timeSpend;
	}

	public String getFollow_up() {
		return follow_up;
	}

	public void setFollow_up(String follow_up) {
		this.follow_up = follow_up;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCodeLagacySystem() {
		return codeLagacySystem;
	}

	public void setCodeLagacySystem(String codeLagacySystem) {
		this.codeLagacySystem = codeLagacySystem;
	}

	public List<OfficeDocument> getFiles() {
		return files;
	}

	public void setFiles(List<OfficeDocument> files) {
		this.files = files;
	}

	public boolean isFavorites() {
		return favorites;
	}

	public void setFavorites(boolean favorites) {
		this.favorites = favorites;
	}

	public boolean isImportant() {
		return important;
	}

	public void setImportant(boolean important) {
		this.important = important;
	}

	public Date getWorkingStart() {
		return workingStart;
	}

	public void setWorkingStart(Date workingStart) {
		this.workingStart = workingStart;
	}

	public Date getWorkingEnd() {
		return workingEnd;
	}

	public void setWorkingEnd(Date workingEnd) {
		this.workingEnd = workingEnd;
	}

	public List<CaseWorker> getCaseWorkers() {
		return caseWorkers;
	}

	public void setCaseWorkers(List<CaseWorker> caseWorkers) {
		this.caseWorkers = caseWorkers;
	}

}
