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
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.appavoc.model.Lawyer;
import com.appavoc.model.Office;
import com.appavoc.service.OfficeService;

import java.util.Optional;

@Component
public class LawyerValidator implements Validator {

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	@Autowired
	private AddressValidator addressValidator;

	@Autowired
	private OfficeService officeService;

	@Override
	public boolean supports(Class<?> classObject) {
		return Lawyer.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Lawyer lawyer = (Lawyer) target;

		// Beans validator
		this.validator.validate(lawyer, errors);

		// Custom validations
		// Validate the office.
		Office nOffice = officeService.getOfficeByOfficeNumber(lawyer.getOfficeNumber());
		String id = nOffice.getId();
		final Optional<Office> office = this.officeService.get(id);
		if (!office.isPresent()) {
			errors.rejectValue("offices", "offices.doesNotExist");
		}

		// Validate Address
		ValidationUtils.invokeValidator(this.addressValidator, lawyer.getUser().getPerson().getLocation().getAddress(), errors);

	}

}
