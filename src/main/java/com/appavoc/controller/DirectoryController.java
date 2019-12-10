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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.Directory;
import com.appavoc.model.Folder;
import com.appavoc.service.DirectoryService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/directory")
public class DirectoryController {

	final Logger logger = LoggerFactory.getLogger(DirectoryController.class);
	
	@Autowired
	private DirectoryService directoryService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public Directory createOrUpdateDirectory(@RequestBody @Valid Directory directory) {
		return directoryService.createOrUpdateDirectory(directory);
	}

	@RequestMapping(value = "/getDirectoryByDirectoryNumber/{directoryNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Directory getDirectoryByDirectoryNumber(@PathVariable long directoryNumber) {
		return directoryService.getDirectoryByDirectoryNumber(directoryNumber);
	}

	@RequestMapping(value = "/delete/{directoryNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteDirectory(@PathVariable long directoryNumber) {
		logger.info("avant delete \n\n");
		directoryService.deleteDirectory(directoryNumber);
		logger.info("apres delete \n");
	}

	@RequestMapping(value = "/getAllDirectory", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Directory> getAlldirectory() {
		return directoryService.getAllDirectory();
	}

	@RequestMapping(value = "/getRecently", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Directory> getRecentlyDirectory() {
		return directoryService.getRecentlyDirectory();
	}

	@RequestMapping(value = "/getDirectoryByCustomer/{customerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<Directory> getdirectoryByCustomerNumber(@PathVariable long customerNumber) {
		return directoryService.getDirectoryOfCustomer(customerNumber);
	}

	@RequestMapping(value = "/searchDirectory/{name}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Directory> searchDirectory(@PathVariable String name) {
		return directoryService.searchDirectoryByDirectoryName(name);
	}
	
	@RequestMapping(value = "/getFolderByDirectory/{directoryNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<Folder> getAllFolder(@PathVariable long directoryNumber) {
		return directoryService.getAllFolder(directoryNumber);
	}
}
