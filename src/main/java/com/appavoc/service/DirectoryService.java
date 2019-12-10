/** 
 * @interface DirectoryService 
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

import com.appavoc.model.Directory;
import com.appavoc.model.Folder;

public interface DirectoryService {

	Directory createOrUpdateDirectory(Directory directory);

	Directory getDirectoryByDirectoryNumber(long directoryNumber);

	List<Directory> getAllDirectory();

	void deleteDirectory(long directoryNumber);

	List<Directory> searchDirectoryByDirectoryName(String name);
	
	/* methode pour afficher le tiroir d'un client donné */
	List<Directory> getDirectoryOfCustomer(long customerNumber);
	
	List<Directory> getRecentlyDirectory();
	
	List<Folder> getAllFolder(long directoryNumber);
	
} 
