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

package com.appavoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.appavoc.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
 
	Customer findCustomerByCustomerNumber(long customerNumber);
	
	void deleteCustomerByCustomerNumber(long customerNumber);

	List<Customer> findAllCustomerByOfficeNumber(long officeNumber);

}
