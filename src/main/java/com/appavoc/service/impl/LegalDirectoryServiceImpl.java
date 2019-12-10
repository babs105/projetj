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

package com.appavoc.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appavoc.model.LegalDirectory;
import com.appavoc.repository.LegalDirectoryRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.LegalDirectoryService;

@Service
public class LegalDirectoryServiceImpl implements LegalDirectoryService {

	final Logger logger = LoggerFactory.getLogger(LegalDirectoryServiceImpl.class);

	@Autowired
	MongoOperations mongo;

	@Autowired 
	LegalDirectoryRepository  legalRepository;

	@Override
	public LegalDirectory createOrUpdateLegalDir(LegalDirectory legaldir) {
		long legalDirectoryNumber = legaldir.getLegalDirectoryNumber();
		LegalDirectory legalStored = getLegalDirectoryByLegalDirNumber(legalDirectoryNumber);
		if(legalDirectoryNumber == 0 || legalStored == null){
			legaldir.setLegalDirectoryNumber(getNextSequence("legalDirectoryNumber"));
		}
		legalRepository.save(legaldir);
		return legaldir;
	}

	@Override
	public LegalDirectory getLegalDirectoryByLegalDirNumber(long legalDirNumber) {
		LegalDirectory legaldirectory = legalRepository.findLegalDirectoryByLegalDirectoryNumber(legalDirNumber);
		return legaldirectory;
	}

	@Override
	public List<LegalDirectory> getAllLegalDirectory() {
		List<LegalDirectory> legaldirectories = legalRepository.findAll();
		return legaldirectories;
	}

	@Override
	public void deleteLegalDirectory(long legalDirNumber) {
		legalRepository.deleteLegalDirectoryByLegalDirectoryNumber(legalDirNumber);
		logger.info("DELETE SUCCESSFULL");
	}

	@Override
	public List<LegalDirectory> searchLegalDirByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		List<LegalDirectory> legaldirectories = mongo.find(query, LegalDirectory.class);
		return legaldirectories;
	}

	@Override
	public List<LegalDirectory> getRecentlyLegalDirectory() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "date"));
		List<LegalDirectory> directories = mongo.find(query, LegalDirectory.class);
		return directories;
	}

	public long getNextSequence(String key) {
		// get sequence id
		Query query = new Query(Criteria.where("_id").is(key));

		// increase sequence id by 1
		Update update = new Update();
		update.inc("seq", 1);

		// return new increased id
		FindAndModifyOptions options = new FindAndModifyOptions();
		options.returnNew(true);

		// this is the magic happened.
		SequenceId seqId = mongo.findAndModify(query, update, options, SequenceId.class);

		return seqId.getSeq();
	}

}
