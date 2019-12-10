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

import com.appavoc.model.Assistant;
import com.appavoc.service.AssistantService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/assistant")
public class AssistantController {

	@Autowired
	private AssistantService assistantService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public Assistant createOrUpdateAssistant(@RequestBody @Valid Assistant assistant) {
		return assistantService.createOrUpdateAssistant(assistant);
	}

	@RequestMapping(value = "/getAssistant/{assistantNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Assistant getAssistantByAssistantNumber(@PathVariable long assistantNumber) {
		return assistantService.getAssistantByAssistantNumber(assistantNumber);
	}

	@RequestMapping(value = "/delete/{assistantNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteAssistant(@PathVariable long assistantNumber) {
		assistantService.deleteAssistant(assistantNumber);
	}

	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<Assistant> searchAssistant(@PathVariable String critere) {
		return assistantService.searchAssistantByName(critere);
	}

	@RequestMapping(value = "/getAllAssistant", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Assistant> getAllAssistant() {
		return assistantService.getAllAssistant();
	}

	@RequestMapping(value = "/getMostActive", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Assistant> getMostActiveAssistant() {
		return assistantService.getMostActiveAssistant();
	}
	
}
