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
import com.appavoc.service.CaseWorkerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/caseWorker")
public class CaseWorkerController {

	@Autowired
	private CaseWorkerService caseWorkerService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public CaseWorker createOrUpdateCaseWorker(@RequestBody CaseWorker caseWorker) {
		return caseWorkerService.createOrUpdateCaseWorker(caseWorker);
	}

	@RequestMapping(value = "/getCaseWorker/{caseWorkerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public CaseWorker getCaseWorkerByCaseWorkerNumber(@PathVariable long caseWorkerNumber) {
		return caseWorkerService.getCaseWorkerByCaseWorkerNumber(caseWorkerNumber);
	}

	@RequestMapping(value = "/delete/{caseWorkerNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteCaseWorker(@PathVariable long caseWorkerNumber) {
		caseWorkerService.deleteCaseWorker(caseWorkerNumber);
	}

	@RequestMapping(value = "/getAllCaseWorker", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<CaseWorker> getAllCaseWorker() {
		return caseWorkerService.getAllCaseWorker();
	}

}
