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

import com.appavoc.model.Subscription;
import com.appavoc.model.Office;
import com.appavoc.model.Payment;
import com.appavoc.service.OfficeService;
import com.appavoc.service.PaymentService;

import java.util.Optional;

@Component
public class SubscriptionValidator implements Validator {

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	@Autowired
	private OfficeService officeService;

	@Autowired
	private PaymentService paymentService;

	@Override
	public boolean supports(Class<?> classObject) {
		return Subscription.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Subscription subscription = (Subscription) target;

		// Beans validator
		this.validator.validate(subscription, errors);

		// Validate the office.
		Office nOffice = officeService.getOfficeByOfficeNumber(subscription.getOfficeNumber());
		String id = nOffice.getId();
		final Optional<Office> office = this.officeService.get(id);
		if (!office.isPresent()) {
			errors.rejectValue("office", "office.doesNotExist");
		}

		//Validate the payment
		Payment nPayment = paymentService.getPaymentByTransactionId(subscription.getTransactionNumber());
		id = nPayment.getId();
		final Optional<Payment> Payment = this.paymentService.get(id);
		if (!Payment.isPresent()) {
			errors.rejectValue("Payment", "Payment.doesNotExist");
		}

	}

}
