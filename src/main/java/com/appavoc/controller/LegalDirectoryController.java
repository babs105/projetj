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

package com.appavoc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.LegalDirectory;
import com.appavoc.service.LegalDirectoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/legalDirectory")
public class LegalDirectoryController {

	@Autowired
	private LegalDirectoryService legalDirectoryService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public LegalDirectory createOrUpdateLegalDirectory(@RequestBody @Valid LegalDirectory legalDirectory) {
		return legalDirectoryService.createOrUpdateLegalDir(legalDirectory);
	}

	@RequestMapping(value = "/getLegalDirectoryByLegalDirectoryNumber/{legalDirNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public LegalDirectory getLegalDirectoryByLegalDirectoryNumber(@PathVariable long legalDirNumber) {
		return legalDirectoryService.getLegalDirectoryByLegalDirNumber(legalDirNumber);
	}

	@RequestMapping(value = "/delete/{legalDirNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteLegalDirectory(@PathVariable long legalDirNumber) {
		legalDirectoryService.deleteLegalDirectory(legalDirNumber);
	}

	@RequestMapping(value = "/getAllLegalDirectory", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<LegalDirectory> getAllLegalDirectory() {
		return legalDirectoryService.getAllLegalDirectory();
	}

	@RequestMapping(value = "/getRecently", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<LegalDirectory> getRecentlyLegalDirectory() {
		return legalDirectoryService.getRecentlyLegalDirectory();
	}

	@RequestMapping(value = "/searchLegalDirectory/{name}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<LegalDirectory> searchLegalDirectory(@PathVariable String name) {
		return legalDirectoryService.searchLegalDirByName(name);
	}
	
}
