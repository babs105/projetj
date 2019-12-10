/** 
 * @interface FolderService 
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

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.appavoc.model.CaseWorker;
import com.appavoc.model.Folder;
import com.appavoc.model.OfficeDocument;

public interface FolderService {

	Folder createOrUpdateFolder(Folder folder);

	Folder getFolderByFolderNumber(long folderNumber);
	
	Folder getFolderByDirectoryNumber(long directoryNumber);

	List<Folder> getAllFolder();

	void deleteFolder(long folderNumber);

	List<Folder> searchFolderByName(String name);
	
	/* methode pour calculer le temps passé d'un dossier */
	double calculationTimeSpent(long folderNumber, Date workingStart,Date WorkingEnd);
	
	/* methode pour afficher les dossiers favoris */
	List<Folder> getFavoriteFolder();
	
	/* methode pour afficher les dossiers d'un client */
	List<Folder> getLawyerFolder(long lawyerNumber);
	
	List<OfficeDocument> getAllDocument(long folderNumber);
	
	List<CaseWorker> getAllCaseWorker(long folderNumber);
	
	List<Folder> getRecentlyFolder();

	/**
	 * @param id
	 * @return
	 */
	Optional<Folder> get(String id);
	
}
