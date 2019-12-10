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

import java.io.File;
import java.util.Date;
import java.util.UUID;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.appavoc.model.enums.RegistrationStatus;

@Document
public class CaseWorker {

	@Id
	String id = UUID.randomUUID().toString();

	@PositiveOrZero
	private int age;
	
	@NotNull
	long caseWorkerNumber;
	
	@NotNull
	long folderNumber;

	private String title;

	private String sex;

	private String firstName;

	private String lastName;

	@Email
	private String personalEmail;
	
	@Email
	private String companyEmail;

	private String name;
	
	private RegistrationStatus registrationStatus;

	//@NotNull
	private String address;

	@NotNull
	private String phone;
	
	@NotNull
	private String secondaryPhone;

	@NotNull
	private String occupation;

	private String ethnicity;

	private String maritalStatus;
	
	private Date lastConnexionTime;

	private File image;

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}


	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}


	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getEthnicity() {
		return ethnicity;
	}

	public void setEthnicity(String ethnicity) {
		this.ethnicity = ethnicity;
	}

	public File getImage() {
		return image;
	}

	public void setImage(File image) {
		this.image = image;
	}

	public String getLabel() {
		return title;
	}

	public void setLabel(String label) {
		this.title = label;
	}
	
	public Date getLastConnexionTime() {
		return lastConnexionTime;
	}

	public void setLastConnexionTime(Date lastConnexionTime) {
		this.lastConnexionTime = lastConnexionTime;
	}
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the registrationStatus
	 */
	public RegistrationStatus getRegistrationStatus() {
		return registrationStatus;
	}

	/**
	 * @param registrationStatus the registrationStatus to set
	 */
	public void setRegistrationStatus(RegistrationStatus registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	/**
	 * @return the caseWorkerNumber
	 */
	public long getCaseWorkerNumber() {
		return caseWorkerNumber;
	}

	/**
	 * @param caseWorkerNumber the caseWorkerNumber to set
	 */
	public void setCaseWorkerNumber(long caseWorkerNumber) {
		this.caseWorkerNumber = caseWorkerNumber;
	}

	/**
	 * @return the folderNumber
	 */
	public long getFolderNumber() {
		return folderNumber;
	}

	/**
	 * @param folderNumber the folderNumber to set
	 */
	public void setFolderNumber(long folderNumber) {
		this.folderNumber = folderNumber;
	}

	/**
	 * @return the personalEmail
	 */
	public String getPersonalEmail() {
		return personalEmail;
	}

	/**
	 * @param personalEmail the personalEmail to set
	 */
	public void setPersonalEmail(String personalEmail) {
		this.personalEmail = personalEmail;
	}

	/**
	 * @return the companyEmail
	 */
	public String getCompanyEmail() {
		return companyEmail;
	}

	/**
	 * @param companyEmail the companyEmail to set
	 */
	public void setCompanyEmail(String companyEmail) {
		this.companyEmail = companyEmail;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param adress the address to set
	 */
	public void setAdress(String adress) {
		address = adress;
	}

	/**
	 * @return the secondaryPhone
	 */
	public String getSecondaryPhone() {
		return secondaryPhone;
	}

	/**
	 * @param secondaryPhone the secondaryPhone to set
	 */
	public void setSecondaryPhone(String secondaryPhone) {
		this.secondaryPhone = secondaryPhone;
	}

	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

}
