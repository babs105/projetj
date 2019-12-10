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

import com.appavoc.model.Customer;
import com.appavoc.service.CustomerService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public Customer createOrUpdateCustomer(@RequestBody Customer customer) {
		return customerService.createOrUpdateCustomer(customer);
	}

	@RequestMapping(value = "/getCustomer/{customerNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public Customer getCustomerByCustomerNumber(@PathVariable long customerNumber) {
		return customerService.getCustomerByCustomerNumber(customerNumber);
	}

	@RequestMapping(value = "/delete/{customerNumber}", method = {RequestMethod.DELETE, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public void deleteCustomer(@PathVariable long customerNumber) {
		customerService.deleteCustomer(customerNumber);
	}

	@RequestMapping(value = "/getAllCustomer", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Customer> getAllCustomer() {
		return customerService.getAllCustomer();
	}

	@RequestMapping(value = "/getMostActive", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Customer> getMostActiveCustomer() {
		return customerService.getMostActiveCustomer();
	}

	@RequestMapping(value = "/getCustomerByOffice/{officeNumber}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Customer> getCustomersOfOffice(@PathVariable long officeNumber) {
		return customerService.getCustomersOfOffice(officeNumber);
	}

	@RequestMapping(value = "/search/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Customer> searchCustomer(@PathVariable String critere) {
		return customerService.searchCustomerByName(critere);
	}
	
	@RequestMapping(value = "/getCustomerOf/{critere}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<Customer> getCustomerByCritere(@PathVariable String critere,@RequestBody List<Customer> customers) {
		return customerService.getfilter(critere, customers);
	}

}
