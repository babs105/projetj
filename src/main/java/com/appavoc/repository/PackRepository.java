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

import com.appavoc.model.Pack;

public interface PackRepository extends MongoRepository<Pack, String> {
 
	Pack findPackByPackNumber(long packNumber);

	void deletePackByPackNumber(long packNumber);
}
