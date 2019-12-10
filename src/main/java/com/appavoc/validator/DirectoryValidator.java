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
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.appavoc.model.Directory;
import com.appavoc.model.Customer;
import com.appavoc.model.Folder;
import com.appavoc.model.Lawyer;
import com.appavoc.model.OfficeDocument;
import com.appavoc.service.CustomerService;
import com.appavoc.service.FolderService;
import com.appavoc.service.LawyerService;

import java.util.Optional;

@Component
public class DirectoryValidator implements Validator {

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private FolderService folderService;

	@Autowired 
	DocumentValidator documentValidator;

	@Autowired
	LawyerValidator lawyerValidator;

	@Autowired
	LawyerService lawyerService;

	@Override
	public boolean supports(Class<?> classObject) {
		return Directory.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Directory directory = (Directory) target;

		// Beans validator
		this.validator.validate(directory, errors);

		// Validate the customer.
		Customer nCustomer = customerService.getCustomerByCustomerNumber(directory.getCustomerNumber());
		String id = nCustomer.getUser().getId();
		final Optional<Customer> customer = this.customerService.get(id);
		if (!customer.isPresent()) {
			errors.rejectValue("customer", "customer.doesNotExist");
		}

		for (final Folder folder : directory.getFolders()) {

			// Validate the folders.
			id = folder.getId();
			final Optional<Folder> folDer = this.folderService.get(id);
			if (!folDer.isPresent()) {
				errors.rejectValue("folders", "folders.doesNotExist");
			}

			// Validate Documents.
			if (CollectionUtils.isNotEmpty(folder.getFiles())) {
				for (final OfficeDocument document : folder.getFiles()) {
					ValidationUtils.invokeValidator(this.documentValidator,document , errors);
				}
			} 

			// Validate Lawyers.
			Lawyer nLawyer = lawyerService.getLawyerByLawyerNumber(folder.getLawyerNumber());
			id = nLawyer.getUser().getId();
			final Optional<Lawyer> lawyer = this.lawyerService.get(id);
			if (!lawyer.isPresent()) {
				errors.rejectValue("lawyer", "lawyer.doesNotExist");
			}
		}

	}

}
