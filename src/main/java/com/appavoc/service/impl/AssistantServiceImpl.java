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

import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.CloudinaryImageService;
import com.appavoc.service.TimelineActivityService;
import com.appavoc.service.UserService;
import com.appavoc.model.Assistant;
import com.appavoc.model.Bill;
import com.appavoc.model.TimelineActivity;
import com.appavoc.model.User;
import com.appavoc.repository.AssistantRepository;
import com.appavoc.service.AssistantService;

@Service
public class AssistantServiceImpl implements AssistantService {

	final Logger logger = LoggerFactory.getLogger(AssistantServiceImpl.class);

	@Autowired
	AssistantRepository assistantRepository;
	
	@Autowired
	TimelineActivityService timelineActivityService;
	
	@Autowired
	UserService userService;

	@Autowired 
	MongoOperations mongo;

	@Autowired
	CloudinaryImageService cloudinaryImageService;

	@Override
	public Assistant createOrUpdateAssistant(Assistant assistant) {
		long assistantNumber = assistant.getAssistantNumber();
		Assistant assistantStored= getAssistantByAssistantNumber(assistantNumber);
		if(assistantNumber == 0 || assistantStored == null ){
			assistant.setAssistantNumber(getNextSequence("assistantNumber"));

			/*
			 * User user = new User(); user.setName(assistant.getName());
			 * user.setUsername(assistant.getUsername());
			 * user.setEmail(assistant.getEmail());
			 * user.setPassword(assistant.getPassword());
			 * user.setConfirmPassword(assistant.getConfirmPassword());
			 * user.setFirstName(assistant.getFirstName());
			 * user.setLastName(assistant.getLastName());
			 * 
			 * User userToCreate = userService.createUser(user);
			 * assistant.setId(userToCreate.getId());
			 */
		}
		
		
		Assistant assistantCreated = assistantRepository.save(assistant);
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Assistant");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(assistantCreated.getAssistantNumber());
		timelineActivity.setUserId(assistant.getUser().getId());
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return assistant;
	}

	@Override
	public Assistant getAssistantByAssistantNumber(long assistantNumber) {
		Assistant assistants = assistantRepository.findAssistantByAssistantNumber(assistantNumber);
		return assistants;
	}

	@Override
	public List<Assistant> getAllAssistant() {
		List<Assistant> assistants = assistantRepository.findAll();
		return assistants;
	}

	@Override
	public void deleteAssistant(long assistantNumber) {
		Assistant assistantToDelete = assistantRepository.findAssistantByAssistantNumber(assistantNumber);
		
		assistantRepository.deleteAssistantByAssistantNumber(assistantNumber);
		
		logger.info("DELETE SUCCESSFULL");
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(assistantNumber);
		timelineActivity.setType("Assistant");
		timelineActivity.setAction("Delete");
		
		timelineActivity.setUserId(assistantToDelete.getUser().getId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
	}

	@Override
	public List<Assistant> searchAssistantByName(String critere) {
		Criteria criteriaV1 = Criteria.where("firstName").is(critere);
		Criteria criteriaV2 = Criteria.where("lastName").is(critere);
		Query query = new Query(new Criteria().orOperator(criteriaV1, criteriaV2));
		List<Assistant> assistants = mongo.find(query, Assistant.class);
		return assistants;
	}

	@Override
	public List<Assistant> getMostActiveAssistant() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "lastConnexionTime"));
		List<Assistant> assistants = mongo.find(query, Assistant.class);
		return assistants;
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
