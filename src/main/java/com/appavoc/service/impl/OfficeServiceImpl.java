/** 
 * @class OfficeServiceImpl 
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

import java.util.ArrayList;
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

import com.appavoc.model.Office;
import com.appavoc.repository.OfficeRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.OfficeService;

@Service
public class OfficeServiceImpl implements OfficeService {

	final Logger logger = LoggerFactory.getLogger(OfficeServiceImpl.class);

	@Autowired 
	MongoOperations mongo;

	@Autowired
	OfficeRepository officeRepository;

	@Override
	public Office createOrUpdateOffice(Office office) {
		long officeNumber = office.getOfficeNumber();
		Office officeStored = getOfficeByOfficeNumber(officeNumber);
		if(officeNumber == 0 || officeStored == null){
			office.setOfficeNumber(getNextSequence("officeNumber"));
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			Date createDate = cal.getTime();
			office.setCreationDate(createDate);
		}
		officeRepository.save(office);
		return office;
	}

	@Override
	public Office getOfficeByOfficeNumber(long officeNumber) {
		Office office = officeRepository.findOfficeByOfficeNumber(officeNumber);
		return office;
	}

	@Override
	public List<Office> getAllOffice() {
		List<Office> Offices = officeRepository.findAll();
		return Offices;
	}

	@Override
	public void deleteOffice(long officeNumber) {
		officeRepository.deleteOfficeByOfficeNumber(officeNumber);
		logger.info("DELETE SUCCESSFULL");
	}

	@Override
	public List<Office> searchOfficeByName(String critere) {
		Criteria criteriaV1 = Criteria.where("name").is(critere);
		Criteria criteriaV2 = Criteria.where("directorName").is(critere);
		Query query = new Query(new Criteria().orOperator(criteriaV1, criteriaV2));
		List<Office> offices = mongo.find(query, Office.class);
		return offices;
	}

	@Override
	public List<Office> getOfficeByCritere(String critere, List<Office> offices) {
		List<Office> office = new ArrayList<Office>();;
		for(int i = 0; i < offices.size(); i++) {
			if(offices.get(i).getAddress().getCity().equals(critere)) {
				 office.add(offices.get(i));
			}
			if(offices.get(i).getAddress().getCountry().equals(critere)) {
				 office.add(offices.get(i));
			}
			if(offices.get(i).getAddress().getDepartment().equals(critere)) {
				 office.add(offices.get(i));
			}
			if(offices.get(i).getAddress().getRegion().equals(critere)) {
				 office.add(offices.get(i));
			}
			if(offices.get(i).getAddress().getVillage().equals(critere)) {
				 office.add(offices.get(i));
			}
		}
		return office;
	}

	@Override
	public List<Office> getMostActiveOffice() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "lastConnexionTime"));
		List<Office> offices = mongo.find(query, Office.class);
		return offices;
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
	public Optional<Office> get(String id) {
		return this.officeRepository.findById(id);
	}

}
