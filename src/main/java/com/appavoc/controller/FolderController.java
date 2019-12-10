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

import java.util.Date;
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

import com.appavoc.model.CaseWorker;
import com.appavoc.model.Folder;
import com.appavoc.model.OfficeDocument;
import com.appavoc.service.FolderService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/folder")
public class FolderController {

	@Autowired
	private FolderService folderService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public Folder createOrUpdateFolder(@RequestBody @Valid Folder folder) {
		return folderService.createOrUpdateFolder(folder);
	}

	@RequestMapping(value = "/getFolderByFolderNumber/{folderNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Folder getFolderByFolderNumber(@PathVariable long folderNumber) {
		return folderService.getFolderByFolderNumber(folderNumber);
	}

	@RequestMapping(value = "/delete/{folderNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteFolder(@PathVariable long folderNumber) {
		folderService.deleteFolder(folderNumber);
	}

	@RequestMapping(value = "/getAllFolder", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Folder> getAllFolder() {
		return folderService.getAllFolder();
	}

	@RequestMapping(value = "/getRecently", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Folder> getRecentlyFolder() {
		return folderService.getRecentlyFolder();
	}

	@RequestMapping(value = "/getFolderByLawyer/{lawyerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Folder> getFolderByLawyerNumber(@PathVariable long lawyerNumber) {
		return folderService.getLawyerFolder(lawyerNumber);
	}

	@RequestMapping(value = "/searchFolder/{name}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Folder> searchFolder(@PathVariable String name) {
		return folderService.searchFolderByName(name);
	}

	@RequestMapping(value = "/getFavorites", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Folder> getFavoriteFolder() {
		return folderService.getFavoriteFolder();
	}
	
	@RequestMapping(value = "/timeSpent/{folderNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public double calculationTimeSpent(@PathVariable long folderNumber, Date workingStart, Date workingEnd ) {
		return folderService.calculationTimeSpent(folderNumber,workingStart,workingEnd);
	}

	@RequestMapping(value = "/getDocumentsByFolder/{folderNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<OfficeDocument> getAllDocuments(@PathVariable long folderNumber) {
		return folderService.getAllDocument(folderNumber);
	}
	
	@RequestMapping(value = "/getCaseWorkerByFolder/{folderNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<CaseWorker> getAllCaseWorker(@PathVariable long folderNumber) {
		return folderService.getAllCaseWorker(folderNumber);
	}
}
