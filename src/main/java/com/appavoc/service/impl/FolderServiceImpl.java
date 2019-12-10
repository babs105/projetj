/** 
 * @class FolderServiceImpl 
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

package com.appavoc.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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

import com.appavoc.model.CaseWorker;
import com.appavoc.model.Folder;
import com.appavoc.model.OfficeDocument;
import com.appavoc.model.TimelineActivity;
import com.appavoc.repository.FolderRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.FolderService;
import com.appavoc.service.TimelineActivityService;

@Service
public class FolderServiceImpl implements FolderService {

	final Logger logger = LoggerFactory.getLogger(FolderServiceImpl.class);

	@Autowired
	MongoOperations mongo;
	
	@Autowired
	TimelineActivityService timelineActivityService;

	@Autowired
	FolderRepository folderRepository;

	@Override
	public Folder createOrUpdateFolder(Folder folder) {
		long folderNumber = folder.getFolderNumber();
		Folder folderStored = getFolderByFolderNumber(folderNumber);
		if(folderNumber == 0 || folderStored == null){
			folder.setFolderNumber(getNextSequence("folderNumber"));
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			Date date = cal.getTime();
			folder.setDate(date);
		}
		Folder folderCeated = folderRepository.save(folder);
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(folderCeated.getFolderNumber());
		timelineActivity.setType("Folder");
		timelineActivity.setAction("Create");
		timelineActivity.setUserId(folder.getUserId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return folder;
	}

	@Override
	public Folder getFolderByFolderNumber(long folderNumber) {
		Folder folder = folderRepository.findFolderByFolderNumber(folderNumber);
		return folder;
	}

	@Override
	public List<Folder> getAllFolder() {
		List<Folder> folders = folderRepository.findAll();
		return folders;
	}

	@Override
	public void deleteFolder(long folderNumber) {
		Folder folderStored = folderRepository.findFolderByFolderNumber(folderNumber);
		folderRepository.deleteFolderByFolderNumber(folderNumber);
		logger.info("DELETE SUCCESSFULL");
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(folderNumber);
		timelineActivity.setType("Folder");
		timelineActivity.setAction("Delete");
		timelineActivity.setUserId(folderStored.getUserId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
	}

	@Override
	public List<Folder> searchFolderByName(String name) {
		Query query = new Query(Criteria.where("name").is(name));
		List<Folder> folders = mongo.find(query, Folder.class);
		return folders;
	}

	@Override
	public List<Folder> getFavoriteFolder() {
		Query query = new Query(Criteria.where("favorites").is(true));
		List<Folder> folders = mongo.find(query, Folder.class);
		return folders;
	}

	@Override
	public List<Folder> getLawyerFolder(long lawyerNumber) {
		List<Folder> folders = folderRepository.findFolderByLawyerNumber(lawyerNumber);
		return folders;	}

	@Override
	public List<Folder> getRecentlyFolder() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "date"));
		List<Folder> folders = mongo.find(query, Folder.class);
		return folders;
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
	public double calculationTimeSpent(long folderNumber,Date workingStart,Date workingEnd) {
		Folder folder = getFolderByFolderNumber(folderNumber);
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		logger.info("CalculTimeSpent : \n");
		if(workingStart == null) {
			folder.setWorkingStart(cal.getTime());
			workingStart = cal.getTime();
			logger.info("WorkingStart : \n"+folder.getWorkingStart());
		  }
		
		if(workingEnd == null) {
			folder.setWorkingEnd(cal.getTime());
			workingEnd = cal.getTime();
			logger.info("WorkingEnd : \n"+folder.getWorkingEnd());
		  }
		Calendar startCal=Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		startCal.setTime(workingStart);
		logger.info("StartCalendar : \n"+startCal);
		Calendar endCal=Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		endCal.setTime(workingEnd);
		logger.info("EndCalendar : \n"+endCal);
		int mns = startCal.get(Calendar.HOUR_OF_DAY) * 60 + startCal.get(Calendar.MINUTE);
		logger.info("StartMinute : \n"+mns);
		int mne = endCal.get(Calendar.HOUR_OF_DAY) * 60 + endCal.get(Calendar.MINUTE);
		logger.info("EndMinute : \n"+mne);
		folder.setTimeSpend(folder.getTimeSpend() + mne - mns);
		logger.info("TimeSpent : \n"+folder.getTimeSpend());
		folder.setWorkingStart(null);
		folder.setWorkingEnd(null);
		folder = createOrUpdateFolder(folder);
		return folder.getTimeSpend();
	}

	@Override
	public Optional<Folder> get(String id) {
		return this.folderRepository.findById(id);
	}

	@Override
	public List<OfficeDocument> getAllDocument(long folderNumber) {
		Folder folder = getFolderByFolderNumber(folderNumber);
		return folder.getFiles();
	}

	@Override
	public List<CaseWorker> getAllCaseWorker(long folderNumber) {
		Folder folder = getFolderByFolderNumber(folderNumber);
		return folder.getCaseWorkers();
	}

	@Override
	public Folder getFolderByDirectoryNumber(long directoryNumber) {
		// TODO Auto-generated method stub
		return folderRepository.findFolderByDirectoryNumber(directoryNumber);
	}

}
