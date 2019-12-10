/** 
 * @interface DocumentService 
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

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.appavoc.model.OfficeDocument;

public interface DocumentService {

	OfficeDocument createOrUpdateDocument(OfficeDocument OfficeDocument);

	OfficeDocument getDocumentByDocNumber(long docNumber);

	List<OfficeDocument> getAllDocument();

	void deleteDocument(long docNumber);
	
	/* methode pour afficher les documents d'un type ou d'un nom donné */
	List<OfficeDocument> searchDocument(String critere);
	
	List<OfficeDocument> getRecentlyDocument();
	
	/* methode pour exporter les documents */
	OfficeDocument importDocument(MultipartFile file , long docNumber) throws IOException;

}
