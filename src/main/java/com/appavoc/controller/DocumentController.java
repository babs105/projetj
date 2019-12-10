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

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
//import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.appavoc.model.OfficeDocument;
import com.appavoc.service.DocumentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private DocumentService documentService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public OfficeDocument createOrUpdateOfficeDocument(@RequestBody @Valid OfficeDocument officeDocument) {
		return documentService.createOrUpdateDocument(officeDocument);
	}

	@RequestMapping(value = "/getOfficeDocument/{docNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public OfficeDocument getOfficeDocumentByOfficeDocumentNumber(@PathVariable long docNumber) {
		return documentService.getDocumentByDocNumber(docNumber);
	}

	@RequestMapping(value = "/delete/{docNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteOfficeDocument(@PathVariable long docNumber) {
		documentService.deleteDocument(docNumber);
	}

	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<OfficeDocument> searchOfficeDocument(@PathVariable String critere) {
		return documentService.searchDocument(critere);
	}

	@RequestMapping(value = "/getAllDocument", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<OfficeDocument> getAllOfficeDocument() {
		return documentService.getAllDocument();
	}

	@RequestMapping(value = "/getRecentlyDoc", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<OfficeDocument> getMostActiveOfficeDocument() {
		return documentService.getRecentlyDocument();
	}
	
	@RequestMapping(value = "/import/{docNumber}", method = {RequestMethod.POST, RequestMethod.OPTIONS },consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer","Role_Customer"})
	public OfficeDocument importDocument(@RequestParam("file") MultipartFile file , @PathVariable long docNumber) throws IOException {
		return documentService.importDocument(file,docNumber);
	}

}
