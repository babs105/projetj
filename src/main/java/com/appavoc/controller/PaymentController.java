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

import com.appavoc.model.Payment;
import com.appavoc.service.PaymentService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Payment createOrUpdatePayment(@RequestBody @Valid Payment Payment) {
		return paymentService.createOrUpdatePayment(Payment);
	}

	@RequestMapping(value = "/getPaymentByTransactionId/{transactionId}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Payment getPaymentByTransactionId(@PathVariable long transactionId) {
		return paymentService.getPaymentByTransactionId(transactionId);
	}

	@RequestMapping(value = "/delete/{transactionId}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public void deletePayment(@PathVariable long transactionId) {
		paymentService.deletePayment(transactionId);
	}

	@RequestMapping(value = "/getAllPayment", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Payment> getAllPayment() {
		return paymentService.getAllPayment();
	}

	@RequestMapping(value = "/getPayed", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Payment> getPayed() {
		return paymentService.getPayed();
	}

	@RequestMapping(value = "/getNoPayed", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Payment> getNoPayed() {
		return paymentService.getNoPayed();
	}

	@RequestMapping(value = "/getDayPayment/{dateStr}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Payment> getPaymentOfDay(@PathVariable String dateStr) {
		return paymentService.getPaymentOfDay(dateStr);
	}

	@RequestMapping(value = "/search/{dateStr}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Payment> searchPayment(@PathVariable String critere) {
		return paymentService.searchPaymentByName(critere);
	}

	@RequestMapping(value = "/getMonthPayment/{month}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Payment> getPaymentOfMonth(@PathVariable int month) {
		return paymentService.getPaymentOfMonth(month);
	}
}
