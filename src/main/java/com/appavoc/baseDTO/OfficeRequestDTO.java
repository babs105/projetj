package com.appavoc.baseDTO;


public class OfficeRequestDTO {
	private String companyName;
	private String telCompany;
	private String firstName;
	private String lastName;
	private String email;
	private String pack;
	private String promotion;
	
	public String getPromotion() {
		return promotion;
	}
	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}
	private String password;
	private String confirmPassword;
	
	
	public String getCompanyName() {
		return companyName;
	}
	public String getPack() {
		return pack;
	}
	public void setPack(String pack) {
		this.pack = pack;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getTelCompany() {
		return telCompany;
	}
	public void setTelCompany(String telCompany) {
		this.telCompany = telCompany;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
