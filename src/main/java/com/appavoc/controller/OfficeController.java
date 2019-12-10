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

import com.appavoc.model.Office;
import com.appavoc.service.OfficeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/office")
public class OfficeController {

	@Autowired
	private OfficeService officeService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Admin"})
	public Office createOrUpdateoffice(@RequestBody @Valid Office office) {
		return officeService.createOrUpdateOffice(office);
	}

	@RequestMapping(value = "/getoffice/{officeNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Office getofficeByofficeNumber(@PathVariable long officeNumber) {
		return officeService.getOfficeByOfficeNumber(officeNumber);
	}

	@RequestMapping(value = "/delete/{officeNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Admin"})
	public void deleteoffice(@PathVariable long officeNumber) {
		officeService.deleteOffice(officeNumber);
	}

	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Office> searchoffice(@PathVariable String critere) {
		return officeService.searchOfficeByName(critere);
	}

	@RequestMapping(value = "/getAlloffice", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Office> getAlloffice() {
		return officeService.getAllOffice();
	}

	@RequestMapping(value = "/getMostActive", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Office> getMostActiveOffice() {
		return officeService.getMostActiveOffice();
	}

	@RequestMapping(value = "/getOfficeOf/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Office> getOfficeByCritere(@PathVariable String critere,@RequestBody List<Office> office) {
		return officeService.getOfficeByCritere(critere,office);
	}

}
