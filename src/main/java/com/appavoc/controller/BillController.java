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

import com.appavoc.model.Bill;
import com.appavoc.service.BillService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bill")
public class BillController {

	@Autowired
	private BillService billService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public Bill createOrUpdateBill(@RequestBody @Valid Bill bill) {
		return billService.createOrUpdateBill(bill);
	}

	@RequestMapping(value = "/getBillByBillNumber/{billNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Bill getBillByBillNumber(@PathVariable long billNumber) {
		return billService.getBillByBillNumber(billNumber);
	}

	@RequestMapping(value = "/delete/{billNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteBill(@PathVariable long billNumber) {
		billService.deleteBill(billNumber);
	}

	@RequestMapping(value = "/getAllBill", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<Bill> getAllBill() {
		return billService.getAllBill();
	}

	@RequestMapping(value = "/getRecently", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Bill> getRecentlyBill() {
		return billService.getRecentlyBill();
	}

	@RequestMapping(value = "/getBillByCustomer/{customerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer","Role_Customer"})
	public List<Bill> getBillsByCustomerNumber(@PathVariable long customerNumber) {
		return billService.getBillsByCustomerNumber(customerNumber);
	}

	@RequestMapping(value = "/getBillByOffice/{officeNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<Bill> getBillsByOfficeNumber(@PathVariable long officeNumber) {
		return billService.getBillsByOfficeNumber(officeNumber);
	}

	@RequestMapping(value = "/getTransaction/{billNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public long getTransactionByBillNumber(@PathVariable long billNumber) {
		return billService.getTransactionByBillNumber(billNumber);
	}

	@RequestMapping(value = "/getFolder/{billNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public long getFolderByBillNumber(@PathVariable long billNumber) {
		return billService.getFolderByBillNumber(billNumber);
	}

	@RequestMapping(value = "/getBillByLawyer/{lawyerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Bill> getBillsByLawyerNumber(@PathVariable long lawyerNumber) {
		return billService.getBillsByLawyerNumber(lawyerNumber);
	}
	
	@RequestMapping(value = "/calculate/{billNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public Bill calculateBill(@PathVariable long billNumber) {
		return billService.calculateBill(billNumber);
	}
	
	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Bill> searchBill(@PathVariable String critere) {
		return billService.searchBill(critere);
	}
	
	@RequestMapping(value = "/billOfMonth/{month}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<Bill> searchBillOfMonth(@PathVariable int month) {
		return billService.searchBillOfMonth(month);
	}

}
