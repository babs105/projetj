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

import com.appavoc.model.Lawyer;

public interface LawyerRepository extends MongoRepository<Lawyer, String> {

	Lawyer findLawyerByLawyerNumber(long lawyerNumber);

	void deleteLawyerByLawyerNumber(long lawyerNumber);
	
	List<Lawyer> findAllLawyerByOfficeNumber(long officeNumber);

}
