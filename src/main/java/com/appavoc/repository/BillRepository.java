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

import com.appavoc.model.Bill;

public interface BillRepository extends MongoRepository<Bill, String> {
 
	Bill findBillByBillNumber(long billNumber);

	List<Bill> findAllBillByCustomerNumber(long customerNumber);
	
	List<Bill> findAllBillByOfficeNumber(long officeNumber);
	
	List<Bill> findAllBillByLawyerNumber(long lawyerNumber);
	
	void deleteBillByBillNumber(long billNumber);

}
