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

import java.util.List;
import com.appavoc.repository.AdministratorRepository;
import com.appavoc.model.Administrator;
import com.appavoc.model.Bill;
import com.appavoc.model.TimelineActivity;
import com.appavoc.model.User;
import com.appavoc.service.AdministratorService;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.CloudinaryImageService;
import com.appavoc.service.TimelineActivityService;
import com.appavoc.service.UserService;

@Service
public class AdministratorServiceImpl implements AdministratorService {

	final Logger logger = LoggerFactory.getLogger(AdministratorServiceImpl.class);

	@Autowired
	AdministratorRepository administratorRepository;

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	TimelineActivityService timelineActivityService;
	
	@Autowired
	UserService userService;

	@Autowired
	CloudinaryImageService cloudinaryImageService;

	@Override
	public Administrator createOrUpdateAdmin(Administrator administrator) {
		long adminNumber = administrator.getAdminNumber();
		Administrator adminStored = getAdminByAdminNumber(adminNumber);
		if(adminNumber == 0 || adminStored == null){
			administrator.setAdminNumber(getNextSequence("adminNumber"));
			
			/*
			 * User user = new User(); user.setName(administrator.getName());
			 * user.setUsername(administrator.getUsername());
			 * user.setEmail(administrator.getEmail());
			 * user.setPassword(administrator.getPassword());
			 * user.setConfirmPassword(administrator.getConfirmPassword());
			 * user.setFirstName(administrator.getFirstName());
			 * user.setLastName(administrator.getLastName());
			 * 
			 * User userToCreate = userService.createUser(user);
			 * administrator.setId(userToCreate.getId());
			 */
		
		}
		Administrator administratorCreated  = administratorRepository.save(administrator);
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Administrator");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(administratorCreated.getAdminNumber());
		timelineActivity.setUserId(administrator.getUser().getId());
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return administrator;
	}

	@Override
	public Administrator getAdminByAdminNumber(long adminNumber) {
		Administrator admins = administratorRepository.findAdministratorByAdminNumber(adminNumber);
		return admins;
	}

	@Override
	public void deleteAdmin(long adminNumber) {
		
		Administrator  administratorToDelete = administratorRepository.findAdministratorByAdminNumber(adminNumber);
		administratorRepository.deleteAdministratorByAdminNumber(adminNumber);
		
		logger.info("DELETE SUCCESSFULL");
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(adminNumber);
		timelineActivity.setType("Administrator");
		timelineActivity.setAction("Delete");
		timelineActivity.setUserId(administratorToDelete.getUser().getId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
	}

	@Override
	public List<Administrator> searchAdmin(String critere) {
		Criteria criteriaV1 = Criteria.where("firstName").is(critere);
		Criteria criteriaV2 = Criteria.where("lastName").is(critere);
		Criteria criteriaV3 = Criteria.where("name").is(critere);
		Query query = new Query(new Criteria().orOperator(criteriaV1, criteriaV2,criteriaV3));
		List<Administrator> admins = mongo.find(query, Administrator.class);
		return admins;
	}

	@Override
	public List<Administrator> getAllAdmin() {
		List<Administrator> admins = administratorRepository.findAll();
		return admins;
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
	public List<Administrator> getMostActiveAdministrator() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "lastConnexionTime"));
		List<Administrator> admins = mongo.find(query, Administrator.class);
		return admins;
	} 

}
