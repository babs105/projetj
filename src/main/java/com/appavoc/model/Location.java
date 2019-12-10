/** 
 * @class Location 
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

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/*
 * la classe Location permet de localiser une addresse et d'avoir la position d'un objet.
 */

public class Location {

	@NotNull
	@Valid
	private Address address;

	private String directions;

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getDirections() {
		return directions;
	}

	public void setDirections(String directions) {
		this.directions = directions;
	}

}
