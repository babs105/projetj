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
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.CloudinaryImageService;
import com.appavoc.service.FolderService;
import com.appavoc.service.TimelineActivityService;
import com.appavoc.model.Bill;
import com.appavoc.model.CaseWorker;
import com.appavoc.model.TimelineActivity;
import com.appavoc.repository.CaseWorkerRepository;
import com.appavoc.service.CaseWorkerService;

@Service
public class CaseWorkerServiceImpl implements CaseWorkerService {

	final Logger logger = LoggerFactory.getLogger(CaseWorkerServiceImpl.class);

	@Autowired
	CaseWorkerRepository caseWorkerRepository;

	@Autowired 
	MongoOperations mongo;
	
	@Autowired
	FolderService folderService;
	
	@Autowired
	TimelineActivityService timelineActivityService;


	@Autowired
	CloudinaryImageService cloudinaryImageService;

	@Override
	public CaseWorker createOrUpdateCaseWorker(CaseWorker caseWorker) {
		long caseWorkerNumber = caseWorker.getCaseWorkerNumber();
		CaseWorker caseWorkerStored= getCaseWorkerByCaseWorkerNumber(caseWorkerNumber);
		if(caseWorkerNumber == 0 || caseWorkerStored == null ){
			caseWorker.setCaseWorkerNumber(getNextSequence("caseWorkerNumber"));
		}
		CaseWorker caseWorkerCreated =caseWorkerRepository.save(caseWorker);
		
		long folderNumber=caseWorker.getFolderNumber();
		String userId = folderService.getFolderByFolderNumber(folderNumber).getUserId();
		
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("CaseWorker");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(caseWorkerCreated.getCaseWorkerNumber());
		timelineActivity.setUserId(userId);
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return caseWorker;
	}

	@Override
	public CaseWorker getCaseWorkerByCaseWorkerNumber(long caseWorkerNumber) {
		CaseWorker caseWorkers = caseWorkerRepository.findCaseWorkerByCaseWorkerNumber(caseWorkerNumber);
		return caseWorkers;
	}

	@Override
	public List<CaseWorker> getAllCaseWorker() {
		List<CaseWorker> caseWorkers = caseWorkerRepository.findAll();
		return caseWorkers;
	}

	@Override
	public void deleteCaseWorker(long caseWorkerNumber) {
		CaseWorker caseWorkerToDelete = caseWorkerRepository.findCaseWorkerByCaseWorkerNumber(caseWorkerNumber) ;
		
		caseWorkerRepository.deleteCaseWorkerByCaseWorkerNumber(caseWorkerNumber);
		
		logger.info("DELETE SUCCESSFULL");
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(caseWorkerNumber);
		timelineActivity.setType("CaseWorker");
		timelineActivity.setAction("Delete");
		
		timelineActivity.setUserId(folderService.getFolderByFolderNumber(caseWorkerToDelete.getFolderNumber()).getUserId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
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
