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

package com.appavoc.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import com.appavoc.model.Address;;

@Component
public class AddressValidator implements Validator {

	private static final Set<String> ISO_COUNTRIES = new HashSet<String>(Arrays.asList(Locale.getISOCountries()));

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	/**
	 * This Validator validates Address instances, and any subclasses of Address too
	 */
	@Override
	public boolean supports(Class<?> classObject) {
		return Address.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Address address = (Address) target;
		// Beans validator
		this.validator.validate(address, errors);
		// Countries validations
		if (!ISO_COUNTRIES.contains(address.getCountry())) {
			errors.rejectValue("address.country", "country.invalid", "The country code is not an ISO 3166-1 alpha-2 code");
		}
	}

}
