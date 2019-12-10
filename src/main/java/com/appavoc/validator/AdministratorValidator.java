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

import com.appavoc.model.Administrator;
import com.appavoc.model.Office;
import com.appavoc.service.OfficeService;

import java.util.Optional;

@Component
public class AdministratorValidator implements Validator {

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	@Autowired
	private AddressValidator addressValidator;

	@Autowired
	private OfficeService officeService;

	@Override
	public boolean supports(Class<?> classObject) {
		return Administrator.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Administrator admin = (Administrator) target;

		// Beans validator
		this.validator.validate(admin, errors);

		// Validate the office.
		Office nOffice = officeService.getOfficeByOfficeNumber(admin.getOfficeNumber());
		String id = nOffice.getId();
		final Optional<Office> office = this.officeService.get(id);
		if (!office.isPresent()) {
			errors.rejectValue("office", "office.doesNotExist");
		}

		// Validate Address
		ValidationUtils.invokeValidator(this.addressValidator, admin.getUser().getPerson().getLocation().getAddress(), errors);

	}

}
