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

import org.joda.time.DateTime;
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

import java.io.ObjectInputStream.GetField;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.appavoc.baseDTO.OfficeRequestDTO;
import com.appavoc.baseDTO.OfficeResponseDTO;
import com.appavoc.controller.command.LoginCommand;
import com.appavoc.model.Administrator;
import com.appavoc.model.Lawyer;
import com.appavoc.model.Office;
import com.appavoc.model.Pack;
import com.appavoc.model.Promotion;
import com.appavoc.model.TimelineActivity;
import com.appavoc.model.User;
import com.appavoc.repository.LawyerRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.AuthService;
import com.appavoc.service.LawyerService;
import com.appavoc.service.OfficeService;
import com.appavoc.service.PackService;
import com.appavoc.service.PromotionService;
import com.appavoc.service.TimelineActivityService;
import com.appavoc.service.UserService;

@Service
public class LawyerServiceImpl implements LawyerService {
	public static final int DATE_VALID = 1;

	final Logger logger = LoggerFactory.getLogger(LawyerServiceImpl.class);

	@Autowired
	MongoOperations mongo;
	
	@Autowired
	AuthService authService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	OfficeService officeService;
	
	@Autowired
	PackService packService;
	
	@Autowired 
	SendingMailService sendingMailService;
	
	@Autowired
	PromotionService promotionService;
	
	@Autowired
	TimelineActivityService timelineActivityService;

	@Autowired
	LawyerRepository lawyerRepository;

	@Override
	public Lawyer createOrUpdateLawyer(Lawyer lawyer) {
		long lawyerNumber = lawyer.getLawyerNumber();
		Lawyer lawyerStored = getLawyerByLawyerNumber(lawyerNumber);
		if(lawyerNumber == 0 || lawyerStored == null){

			lawyer.setLawyerNumber(getNextSequence("lawyerNumber"));	   
			User userToCreate  = userService.createUser(lawyer.getUser());
			lawyer.setUser(userToCreate);

		}
	    
		Lawyer lawyerCreated  = lawyerRepository.save(lawyer);
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Lawyer");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(lawyerCreated.getLawyerNumber());
		timelineActivity.setUserId(lawyer.getUser().getId());
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		
		return lawyer;
	}
	
	@Override
	public OfficeResponseDTO createLawyerRegistration(OfficeRequestDTO officeRequestDto) throws Exception {
		
		Office office = new Office();
		office.setName(officeRequestDto.getCompanyName());
		office.setPhone(officeRequestDto.getTelCompany());
		Office  officeCreated = officeService.createOrUpdateOffice(office);
		
		
		User user = new User();
		user.setFirstName(officeRequestDto.getFirstName());
		user.setLastName(officeRequestDto.getLastName());
		user.setCode(UUID.randomUUID().toString());
		user.setExpiryCode(DateTime.now().plusDays(DATE_VALID));
		user.setEmail(officeRequestDto.getEmail());
		user.setPassword(officeRequestDto.getPassword());
		user.setConfirmPassword(officeRequestDto.getConfirmPassword());
		
		
		Lawyer lawyer = new Lawyer();
		lawyer.setOfficeNumber(officeCreated.getOfficeNumber());
		lawyer.setUser(user);
		Lawyer lawyerCreated = createOrUpdateLawyer(lawyer);
		
		
		Pack pack = new Pack();
		pack.setType("premium");
		pack.setLawyerNumber(lawyerCreated.getLawyerNumber());
	    Pack packCreated = packService.createOrUpdatePack(pack);
	    Promotion promotion = new Promotion();
	    promotion.setPackNumber(packCreated.getPackNumber());
	    promotion.setName("DEMO");
	    Promotion promotionCreated = promotionService.createOrUpdatePromotion(promotion);
	    
	    
	    
	    String tokenEmail = authService.getNewEmailTokenForUser(lawyerCreated.getUser(), lawyerCreated.getUser().getExpiryCode(), lawyerCreated.getUser().getCode());
	    sendingMailService.sendVerificationMail(lawyerCreated.getUser().getEmail(), tokenEmail);
	    
	    
	    officeRequestDto.setCompanyName(officeCreated.getName());
	    officeRequestDto.setTelCompany(officeCreated.getPhone());
	    officeRequestDto.setLastName(lawyerCreated.getUser().getLastName());
	    officeRequestDto.setFirstName(lawyerCreated.getUser().getFirstName());
	    officeRequestDto.setEmail(lawyerCreated.getUser().getEmail());
	    officeRequestDto.setPack(packCreated.getType());
	    officeRequestDto.setPromotion(promotionCreated.getName());
	    officeRequestDto.setPassword("");
	    officeRequestDto.setConfirmPassword("");
	    
	    
	    String cookieContents = authService.getNewLoginCookieContentsForUser(lawyerCreated.getUser(),true);
	    OfficeResponseDTO officeResponseDto = new OfficeResponseDTO(officeRequestDto,cookieContents);
	   
		
		return officeResponseDto;
		
	}
	
	@Override
	public Lawyer getLawyerByLawyerNumber(long lawyerNumber) {
		Lawyer lawyer = lawyerRepository.findLawyerByLawyerNumber(lawyerNumber);
		return lawyer;
	}

	@Override
	public List<Lawyer> getAllLawyer() {
		List<Lawyer> lawyers = lawyerRepository.findAll();
		return lawyers;
	}

	@Override
	public void deleteLawyer(long lawyerNumber) {
		lawyerRepository.deleteLawyerByLawyerNumber(lawyerNumber);
		logger.info("DELETE SUCCESSFULL");
	}

	@Override
	public List<Lawyer> searchLawyerByName(String critere) {
		Criteria criteriaV1 = Criteria.where("firstName").is(critere);
		Criteria criteriaV2 = Criteria.where("lastName").is(critere);
		Query query = new Query(new Criteria().orOperator(criteriaV1, criteriaV2));
		List<Lawyer> lawyers = mongo.find(query, Lawyer.class);
		return lawyers;
	}

	@Override
	public List<Lawyer> getMostActiveLawyer() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "lastConnexionTime"));
		List<Lawyer> lawyers = mongo.find(query, Lawyer.class);
		return lawyers;
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
	public List<Lawyer> getLawyersByOfficeNumber(long officeNumber) {
		List<Lawyer> lawyers = lawyerRepository.findAllLawyerByOfficeNumber(officeNumber);
		return lawyers;
	}

	@Override
	public Optional<Lawyer> get(String id) {
		return this.lawyerRepository.findById(id);
	}

}
