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
import com.appavoc.model.OfficeDocument;

public interface OfficeDocumentRepository extends MongoRepository<OfficeDocument, String> {

	OfficeDocument findOfficeDocumentByDocNumber(long docNumber);

	void deleteOfficeDocumentByDocNumber(long docNumber);

}
