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

import com.appavoc.model.Directory;

public interface DirectoryRepository extends MongoRepository<Directory, String> {

	Directory findDirectoryByDirectoryNumber(long directoryNumber);

	void deleteDirectoryByDirectoryNumber(long directoryNumber);

	List<Directory> findAllDirectoryByCustomerNumber(long customerNumber);

}
