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

import com.appavoc.model.Payment;
import com.appavoc.model.Pack;
import com.appavoc.service.PackService;

import java.util.Optional;

@Component
public class PaymentValidator implements Validator {

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	@Autowired
	private AddressValidator addressValidator;

	@Autowired
	private PackService packService;

	@Override
	public boolean supports(Class<?> classObject) {
		return Payment.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Payment payment = (Payment) target;

		// Beans validator
		this.validator.validate(payment, errors);

		// Validate the pack.
		Pack nPack = packService.getPackByPackNumber(payment.getPackNumber());
		String id = nPack.getId();
		final Optional<Pack> pack = this.packService.get(id);
		if (!pack.isPresent()) {
			errors.rejectValue("packs", "packs.doesNotExist");
		}

		// Validate Address
		ValidationUtils.invokeValidator(this.addressValidator, payment.getBillingAddress(), errors);

	}

}
