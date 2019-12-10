/** 
 * @interface LegalDirectoryService 
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

import com.appavoc.model.LegalDirectory;

public interface LegalDirectoryService {

	LegalDirectory createOrUpdateLegalDir(LegalDirectory legaldir);

	LegalDirectory getLegalDirectoryByLegalDirNumber(long legalDirNumber);
	
	List<LegalDirectory> getAllLegalDirectory();
	
	void deleteLegalDirectory(long legalDirNumber);  
	
	List<LegalDirectory> searchLegalDirByName(String name);
	
	List<LegalDirectory> getRecentlyLegalDirectory();

}
