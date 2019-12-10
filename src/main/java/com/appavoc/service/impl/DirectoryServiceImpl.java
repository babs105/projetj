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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

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

import com.appavoc.model.Directory;
import com.appavoc.model.Folder;
import com.appavoc.model.TimelineActivity;
import com.appavoc.repository.DirectoryRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.DirectoryService;
import com.appavoc.service.FolderService;
import com.appavoc.service.TimelineActivityService;

@Service
public class DirectoryServiceImpl implements DirectoryService {

	final Logger logger = LoggerFactory.getLogger(DirectoryServiceImpl.class);

	@Autowired
	MongoOperations mongo;
	
	@Autowired
	FolderService folderService;
  
	@Autowired
	TimelineActivityService timelineActivityService;

	@Autowired
	DirectoryRepository directoryRepository;

	@Override
	public Directory createOrUpdateDirectory(Directory directory) {
		long directoryNumber = directory.getDirectoryNumber();
		Directory directoryStored = getDirectoryByDirectoryNumber(directoryNumber);
		if(directoryNumber == 0 || directoryStored == null){
			directory.setDirectoryNumber(getNextSequence("directoryNumber"));
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			Date createDate = cal.getTime();
			directory.setDate(createDate);
		}
		Directory directoryCreated = directoryRepository.save(directory);
		
		String userId = folderService.getFolderByDirectoryNumber(directoryCreated.getDirectoryNumber()).getUserId();
		
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Directory");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(directoryCreated.getDirectoryNumber());
		timelineActivity.setUserId(userId);
		timelineActivityService.createTimelineActivity(timelineActivity);
		return directory;
	}

	@Override
	public Directory getDirectoryByDirectoryNumber(long directoryNumber) {
		Directory directory = directoryRepository.findDirectoryByDirectoryNumber(directoryNumber);
		return directory;
	}

	@Override
	public List<Directory> getAllDirectory() {
		List<Directory> directories = directoryRepository.findAll();
		return directories;
	}

	@Override
	public void deleteDirectory(long directoryNumber) {
		directoryRepository.deleteDirectoryByDirectoryNumber(directoryNumber);
		
		logger.info("DELETE SUCCESSFULL");
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(directoryNumber);
		timelineActivity.setType("Directory");
		timelineActivity.setAction("Delete");
		timelineActivity.setUserId(folderService.getFolderByDirectoryNumber(directoryNumber).getUserId());
		timelineActivityService.createTimelineActivity(timelineActivity);
		
	}

	@Override
	public List<Directory> searchDirectoryByDirectoryName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		List<Directory> directories = mongo.find(query, Directory.class);
		return directories;
	}

	@Override
	public List<Directory> getDirectoryOfCustomer(long customerNumber) {
		List<Directory> directories = directoryRepository.findAllDirectoryByCustomerNumber(customerNumber);
		return directories;
	}

	@Override
	public List<Directory> getRecentlyDirectory() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "date"));
		List<Directory> directories = mongo.find(query, Directory.class);
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

	@Override
	public List<Folder> getAllFolder(long directoryNumber) {
		Directory directory = getDirectoryByDirectoryNumber(directoryNumber);
		return directory.getFolders();
	}

}
