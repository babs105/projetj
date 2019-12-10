/** 
 * @interface CaseWorkerService 
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

import com.appavoc.model.CaseWorker;

public interface CaseWorkerService {

	/* methode pour creer ou mettre à jour un intervenant*/
	CaseWorker createOrUpdateCaseWorker(CaseWorker CaseWorker);

	/* methode pour afficher un intervenant grace à son attribut CaseWorkerNumber*/
	CaseWorker getCaseWorkerByCaseWorkerNumber(long CaseWorkerNumber);

	/* methode pour supprimer un intervenant grace à son attribut CaseWorkerNumber*/
	void deleteCaseWorker(long CaseWorkerNumber);
	
	/* methode pour afficher tous les intervenants*/
	List<CaseWorker> getAllCaseWorker();
	
} 
