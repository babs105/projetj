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
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.Promotion;
import com.appavoc.service.PromotionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/promotion")
public class PromotionController {

	@Autowired
	private PromotionService promotionService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Promotion createOrUpdatePromotion(@RequestBody @Valid Promotion Promotion) {
		return promotionService.createOrUpdatePromotion(Promotion);
	}

	@RequestMapping(value = "/getPromotionByPromotionNumber/{promotionNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Promotion getPromotionByPromotionNumber(@PathVariable long promotionNumber) {
		return promotionService.getPromotionByPromotionNumber(promotionNumber);
	}

	@RequestMapping(value = "/delete/{promotionNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public void deletePromotion(@PathVariable long promotionNumber) {
		promotionService.deletePromotion(promotionNumber);
	}

	@RequestMapping(value = "/getAllPromotion", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Promotion> getAllPromotion() {
		return promotionService.getAllPromotion();
	}

	@RequestMapping(value = "/getEndDate", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Date getEndDate(Promotion promotion) {
		return promotionService.getEndDate(promotion);
	}

	@RequestMapping(value = "/search/{transactionNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Promotion searchPromotion(@PathVariable long transactionNumber) {
		return promotionService.searchPromotionByTransaction(transactionNumber);
	}

}
