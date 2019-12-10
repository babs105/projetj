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

import com.appavoc.baseDTO.OfficeRequestDTO;
import com.appavoc.baseDTO.OfficeResponseDTO;
import com.appavoc.model.Lawyer;
import com.appavoc.service.LawyerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/lawyer")
public class LawyerController {

	@Autowired
	private LawyerService lawyerService;

	/*
	 * @RequestMapping(value = "/create", method = {RequestMethod.POST,
	 * RequestMethod.OPTIONS }, produces = "application/json")
	 * 
	 * @ResponseBody //@Secured ({"Role_Admin"}) public Lawyer
	 * createOrUpdateLawyer(@RequestBody @Valid Lawyer lawyer) { return
	 * lawyerService.createOrUpdateLawyer(lawyer); }
	 */
	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public OfficeResponseDTO createLawyerRegistration(@RequestBody @Valid OfficeRequestDTO officeRequestDto) throws Exception {
		return lawyerService.createLawyerRegistration(officeRequestDto);
	}
	

	@RequestMapping(value = "/getLawyerByLawyerNumber/{lawyerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Lawyer getLawyerByLawyerNumber(@PathVariable long lawyerNumber) {
		return lawyerService.getLawyerByLawyerNumber(lawyerNumber);
	}

	@RequestMapping(value = "/delete/{lawyerNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Admin"})
	public void deleteLawyer(@PathVariable long lawyerNumber) {
		lawyerService.deleteLawyer(lawyerNumber);
	}

	@RequestMapping(value = "/getAllLawyer", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Lawyer> getAllLawyer() {
		return lawyerService.getAllLawyer();
	}

	@RequestMapping(value = "/getMostActive", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Lawyer> getMostActiveLawyer() {
		return lawyerService.getMostActiveLawyer();
	}

	@RequestMapping(value = "/getLawyerByOffice/{officeNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Lawyer> getLawyersByOfficeNumber(@PathVariable long officeNumber) {
		return lawyerService.getLawyersByOfficeNumber(officeNumber);
	}

	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Lawyer> searchLawyer(@PathVariable String critere) {
		return lawyerService.searchLawyerByName(critere);
	}

}
