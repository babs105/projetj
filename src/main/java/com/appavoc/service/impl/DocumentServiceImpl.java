/** 
 * @interface DocumentServiceImpl 
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

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appavoc.model.Bill;
import com.appavoc.model.OfficeDocument;
import com.appavoc.model.TimelineActivity;
import com.appavoc.repository.OfficeDocumentRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.DocumentService;
import com.appavoc.service.FolderService;
import com.appavoc.service.TimelineActivityService;

@Service
public class DocumentServiceImpl implements DocumentService {

	final Logger logger = LoggerFactory.getLogger(DocumentServiceImpl.class);

	@Autowired
	MongoOperations mongo;

	@Autowired
	TimelineActivityService timelineActivityService;
	
	@Autowired
	FolderService folderService;
	
	@Autowired
	OfficeDocumentRepository documentRepository;

	@Override
	public OfficeDocument createOrUpdateDocument(OfficeDocument officeDocument) {
		long docNumber = officeDocument.getDocNumber();
		OfficeDocument documentStored = getDocumentByDocNumber(docNumber);
		if(docNumber == 0 || documentStored == null){
			officeDocument.setDocNumber(getNextSequence("docNumber"));
		}
		OfficeDocument officeDocumentCreated = documentRepository.save(officeDocument);
		
		long folderNumber=officeDocument.getFolderNumber();
		String userId = folderService.getFolderByFolderNumber(folderNumber).getUserId();
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("OfficeDocument");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(officeDocumentCreated.getDocNumber());
		timelineActivity.setUserId(userId);
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return officeDocument;
	}

	@Override
	public OfficeDocument getDocumentByDocNumber(long docNumber) {
		OfficeDocument document = documentRepository.findOfficeDocumentByDocNumber(docNumber);
		return document;
	}

	@Override
	public List<OfficeDocument> getAllDocument() {
		List<OfficeDocument> documents = documentRepository.findAll();
		return documents;
	}

	@Override
	public void deleteDocument(long docNumber) {
		OfficeDocument officeDocumentToDelete = documentRepository.findOfficeDocumentByDocNumber(docNumber);
		documentRepository.deleteOfficeDocumentByDocNumber(docNumber);
		logger.info("DELETE SUCCESSFULL");
		

		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(docNumber);
		timelineActivity.setType("OfficeDocument");
		timelineActivity.setAction("Delete");
		
		timelineActivity.setUserId(folderService.getFolderByFolderNumber(officeDocumentToDelete.getFolderNumber()).getUserId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
	}

	@Override
	public List<OfficeDocument> searchDocument(String critere) {
		Query query = new Query(Criteria.where("name").is(critere));
		List<OfficeDocument> documents = mongo.find(query, OfficeDocument.class);
		return documents;
	}

	@Override
	public List<OfficeDocument> getRecentlyDocument() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "Date"));
		List<OfficeDocument> documents = mongo.find(query, OfficeDocument.class);
		return documents;
	}


	@Override
	public OfficeDocument importDocument(MultipartFile file , long docNumber) throws IOException {
	   OfficeDocument doc = getDocumentByDocNumber(docNumber);
	   doc.setName(file.getOriginalFilename());
	   doc.setDocument(file.getBytes());
	   doc.setType(file.getContentType());
	   createOrUpdateDocument(doc);
	   return doc;
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
