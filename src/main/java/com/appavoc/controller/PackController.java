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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.Pack;
import com.appavoc.service.PackService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/pack")
public class PackController {

	@Autowired
	private PackService packService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Pack createOrUpdatePack(@RequestBody @Valid Pack Pack) {
		return packService.createOrUpdatePack(Pack);
	}

	@RequestMapping(value = "/getPackByPackNumber/{packNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Pack getPackByPackNumber(@PathVariable long packNumber) {
		return packService.getPackByPackNumber(packNumber);
	}

	@RequestMapping(value = "/delete/{packNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public void deletePack(@PathVariable long packNumber) {
		packService.deletePack(packNumber);
	}

	@RequestMapping(value = "/getAllPack", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Pack> getAllPack() {
		return packService.getAllPack();
	}

	@RequestMapping(value = "/search/{numberOfUsers}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Pack searchPack(int numberOfUsers) {
		return packService.searchPackByNumberOfUsers(numberOfUsers);
	}

	@RequestMapping(value = "/getPrice/{numberOfUsers}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Pack calculatePrice(int numberOfUsers) {
		return packService.calculatePrice(numberOfUsers);
	}


}
