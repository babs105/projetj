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
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.appavoc.repository.PackRepository;
import com.appavoc.model.Pack;
import com.appavoc.service.PackService;
import com.appavoc.repository.sequence.SequenceId;

@Service
public class PackServiceImpl implements PackService {

	final Logger logger = LoggerFactory.getLogger(PackServiceImpl.class);

	@Autowired
	PackRepository packRepository;

	@Autowired
	private MongoOperations mongo;


	@Override
	public Pack createOrUpdatePack(Pack pack) {
		long packNumber = pack.getPackNumber();
		Pack packStored = getPackByPackNumber(packNumber);
		if(packNumber == 0 || packStored == null){
			pack.setPackNumber(getNextSequence("packNumber"));
		}
		packRepository.save(pack);
		return pack;
	}

	@Override
	public Pack getPackByPackNumber(long packNumber) {
		Pack packs = packRepository.findPackByPackNumber(packNumber);
		return packs;
	}

	@Override
	public List<Pack> getAllPack() {
		List<Pack> packs = packRepository.findAll();
		return packs;
	}

	@Override
	public void deletePack(long packNumber) {
		packRepository.deletePackByPackNumber(packNumber);
		logger.info("DELETE SUCCESSFULL");
	}

	@Override
	public Pack calculatePrice(int numberOfUsers) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pack searchPackByNumberOfUsers(int numberOfUsers) {
		Query query = new Query( Criteria.where("numberOfUsers").is(numberOfUsers));
		Pack pack = mongo.findOne(query, Pack.class);
		return pack;
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
	public Optional<Pack> get(String id) {
		return this.packRepository.findById(id);
	}

}
