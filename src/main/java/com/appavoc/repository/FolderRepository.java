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

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.appavoc.model.Folder;

public interface FolderRepository extends MongoRepository<Folder, String> {

	Folder findFolderByFolderNumber(long folderNumber);
	
	Folder findFolderByDirectoryNumber(long directoryrNumber);
	
	void deleteFolderByFolderNumber(long folderNumber);

	List<Folder> findFolderByLawyerNumber(long customerNumber);

}
