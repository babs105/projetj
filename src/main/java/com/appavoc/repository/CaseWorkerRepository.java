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

import com.appavoc.model.CaseWorker;

public interface CaseWorkerRepository extends MongoRepository<CaseWorker, String> {
 
	CaseWorker findCaseWorkerByCaseWorkerNumber(long caseWorkerNumber);
	
	void deleteCaseWorkerByCaseWorkerNumber(long caseWorkerNumber);
	
} 

