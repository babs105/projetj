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

import org.springframework.data.mongodb.repository.MongoRepository;

import com.appavoc.model.Administrator;

public interface AdministratorRepository extends MongoRepository<Administrator, String> {
 
	Administrator findAdministratorByAdminNumber(long adminNumber);
	
	void deleteAdministratorByAdminNumber(long AdminNumber);
	
} 

