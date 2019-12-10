/** 
 * @interface CustomerService 
 */

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

package com.appavoc.service;

import java.util.List;
import java.util.Optional;

import com.appavoc.model.Customer;

public interface CustomerService {

	Customer createOrUpdateCustomer(Customer customer);

	Customer getCustomerByCustomerNumber(long customerNumber);

	List<Customer> getAllCustomer();

	void deleteCustomer(long customerNumber);
	
	List<Customer> searchCustomerByName(String critere);
	
	List<Customer> getMostActiveCustomer();
	
	/* methode pour filtrer les clients ayant un critere particulier dans une liste donné */
	List<Customer> getfilter(String critere, List<Customer> customers);
	
	/* methode pour afficher tous les clients d'un cabinet donné */
	List<Customer> getCustomersOfOffice(long officeNumber);

	/**
	 * @param id
	 * @return
	 */
	Optional<Customer> get(String id);

}
