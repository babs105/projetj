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

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.Administrator;
import com.appavoc.service.AdministratorService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/administrator")
public class AdministratorController {

	@Autowired
	private AdministratorService administratorService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	@RolesAllowed({"Role_Admin"})
	public Administrator createOrUpdateAdmin(@RequestBody @Valid Administrator administrator) {
		return administratorService.createOrUpdateAdmin(administrator);
	}

	@RequestMapping(value = "/getAdmin/{adminNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Administrator getAdminByAdminNumber(@PathVariable long adminNumber) {
		return administratorService.getAdminByAdminNumber(adminNumber);
	}

	@RequestMapping(value = "/delete/{adminNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	@RolesAllowed({"Role_Admin"})
	public void deleteAdmin(@PathVariable long adminNumber) {
		administratorService.deleteAdmin(adminNumber);
	}

	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	@RolesAllowed({"Role_Admin"})
	public List<Administrator> searchAdmin(@PathVariable String critere) {
		return administratorService.searchAdmin(critere);
	}

	@RequestMapping(value = "/getAllAdmin", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Administrator> getAllAdmin() {
		return administratorService.getAllAdmin();
	}

	@RequestMapping(value = "/getMostActive", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Administrator> getMostActiveAdministrator() {
		return administratorService.getMostActiveAdministrator();
	}

}
