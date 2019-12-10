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

import com.appavoc.model.Office;

public interface OfficeRepository extends MongoRepository<Office, String> {

	Office findOfficeByOfficeNumber(long officeNumber);

	void deleteOfficeByOfficeNumber(long officeNumber);

}
