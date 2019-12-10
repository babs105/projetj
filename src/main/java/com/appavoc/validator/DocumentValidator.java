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

import com.appavoc.model.OfficeDocument;
import com.appavoc.model.Folder;
import com.appavoc.service.FolderService;

import java.util.Optional;

@Component
public class DocumentValidator implements Validator {

	@Autowired
	@Qualifier("mvcValidator")
	private Validator validator;

	@Autowired
	private FolderService folderService;

	@Override
	public boolean supports(Class<?> classObject) {
		return OfficeDocument.class.isAssignableFrom(classObject);
	}

	@Override
	public void validate(Object target, Errors errors) {
		OfficeDocument document = (OfficeDocument) target;

		// Beans validator
		this.validator.validate(document, errors);

		// Validate the folder.
		Folder nFolder = folderService.getFolderByFolderNumber(document.getFolderNumber());
		String id = nFolder.getId();
		final Optional<Folder> folder = this.folderService.get(id);
		if (!folder.isPresent()) {
			errors.rejectValue("folder", "folders.doesNotExist");
		}

	}

}
