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

import com.appavoc.model.Bill;
import com.appavoc.model.Customer;
import com.appavoc.model.Folder;
import com.appavoc.model.Lawyer;
import com.appavoc.model.Office;
import com.appavoc.service.CustomerService;
import com.appavoc.service.FolderService;
import com.appavoc.service.LawyerService;
import com.appavoc.service.OfficeService;

import java.util.Optional;

@Component
public class BillValidator implements Validator {

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private LawyerService lawyerService;

	@Autowired
	private OfficeService officeService;

	@Override
	public boolean supports(Class<?> classObject) {
		return Bill.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Bill bill = (Bill) target;

		// Beans validator
		this.validator.validate(bill, errors);

		// Validate the office.
		Office nOffice = officeService.getOfficeByOfficeNumber(bill.getOfficeNumber());
		String id = nOffice.getId();
		final Optional<Office> office = this.officeService.get(id);
		if (!office.isPresent()) {
			errors.rejectValue("office", "office.doesNotExist");
		}

		// Validate the customer.
		Customer nCustomer = customerService.getCustomerByCustomerNumber(bill.getCustomerNumber());
	     id = nCustomer.getUser().getId();
		final Optional<Customer> customer = this.customerService.get(id);
		if (!customer.isPresent()) {
			errors.rejectValue("customer", "customer.doesNotExist");
		}
		
		// Validate the folder.
		Folder nFolder = folderService.getFolderByFolderNumber(bill.getFolderNumber());
	     id = nFolder.getId();
		final Optional<Folder> folder = this.folderService.get(id);
		if (!folder.isPresent()) {
			errors.rejectValue("folder", "folder.doesNotExist");
		}
		
		// Validate the lawyer.
		Lawyer nLawyer = lawyerService.getLawyerByLawyerNumber(bill.getLawyerNumber());
	     id = nLawyer.getUser().getId();
		final Optional<Lawyer> lawyer = this.lawyerService.get(id);
		if (!lawyer.isPresent()) {
			errors.rejectValue("lawyer", "lawyer.doesNotExist");
		}

	}

}
