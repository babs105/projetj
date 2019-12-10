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

import com.appavoc.model.LegalDirectory;

public interface LegalDirectoryRepository extends MongoRepository<LegalDirectory, String> {

	LegalDirectory findLegalDirectoryByLegalDirectoryNumber(long legalDirectoryNumber);

	void deleteLegalDirectoryByLegalDirectoryNumber(long legalDirNumber);

}
