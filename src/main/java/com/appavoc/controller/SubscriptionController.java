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

import com.appavoc.model.Subscription;
import com.appavoc.service.SubscriptionService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

	@Autowired
	private SubscriptionService subscriptionService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Subscription createOrUpdateSubscription(@RequestBody @Valid Subscription subscription) {
		return subscriptionService.createOrUpdateSubscription(subscription);
	}

	@RequestMapping(value = "/getSubscriptionBySubscriptionNumber/{subscriptionNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Subscription getSubscriptionBySubscriptionNumber(@PathVariable long subscriptionNumber) {
		return subscriptionService.getSubscriptionBySubscriptionNumber(subscriptionNumber);
	}

	@RequestMapping(value = "/delete/{subscriptionNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public void deleteSubscription(@PathVariable long subscriptionNumber) {
		subscriptionService.deleteSubscription(subscriptionNumber);
	}

	@RequestMapping(value = "/getAllSubscription", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Subscription> getAllSubscription() {
		return subscriptionService.getAllSubscription();
	}

	@RequestMapping(value = "/getByDuration/{duration}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Subscription> getSubscriptionByDay(int duration) {
		return subscriptionService.getSubscriptionsByDuration(duration);
	}

	@RequestMapping(value = "/getBypack/{Pack}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Subscription> getSubscriptionOfPack(String pack) {
		return subscriptionService.getSubscriptionsOfPack(pack);
	}

	@RequestMapping(value = "/getDaySubscription/{dateStr}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Subscription> getPayementOfDay(@PathVariable String dateStr) {
		return subscriptionService.getSubscriptionsOfDay(dateStr);
	}

	@RequestMapping(value = "/search/{officeNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Subscription searchSubscription(@PathVariable long officeNumber) {
		return subscriptionService.searchSubscriptionByOffice(officeNumber);
	}

	@RequestMapping(value = "/getEndDate", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Date getEndOfSubscription(Subscription subscription) {
		return subscriptionService.getEndOfSubscription(subscription);
	}
}
