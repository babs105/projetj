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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

import com.appavoc.repository.PromotionRepository;
import com.appavoc.model.Promotion;
import com.appavoc.service.PromotionService;
import com.appavoc.repository.sequence.SequenceId;

@Service
public class PromotionServiceImpl implements PromotionService{

	final Logger logger = LoggerFactory.getLogger(PromotionServiceImpl.class);

	@Autowired
	MongoOperations mongo;

	@Autowired 
	PromotionRepository promotionRepository;

	@Override
	public Promotion createOrUpdatePromotion(Promotion promotion) {
		long promotionNumber = promotion.getPromotionNumber();
		Promotion promotionStored = getPromotionByPromotionNumber(promotionNumber);
		if(promotionNumber == 0 || promotionStored == null){
			promotion.setPromotionNumber(getNextSequence("promotionNumber"));
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			Date date = cal.getTime();
			promotion.setDate(date);
		}
		promotionRepository.save(promotion);
		return promotion;
	}

	@Override
	public Promotion getPromotionByPromotionNumber(long promotionNumber) {
		Promotion promotion = promotionRepository.findPromotionByPromotionNumber(promotionNumber);
		return promotion;
	}

	@Override
	public List<Promotion> getAllPromotion() {
		List<Promotion> promotions = promotionRepository.findAll();
		return promotions;
	}

	@Override
	public void deletePromotion(long promotionNumber) {
		promotionRepository.deletePromotionByPromotionNumber(promotionNumber);
		logger.info("DELETE SUCCESSFULL");
	}

	@Override
	public Promotion searchPromotionByTransaction(long transactionNumber) {
		Query query = new Query(Criteria.where("transactionNumber").is(transactionNumber));
		Promotion promotion = mongo.findOne(query, Promotion.class);
		return promotion;
	}

	@Override
	public List<Promotion> getPromotionByType(String type) {
		Query query = new Query(Criteria.where("type").is(type));
		List<Promotion> promotions = mongo.find(query, Promotion.class);
		return promotions;
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
	public Date getEndDate(Promotion promotion) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.setTime(promotion.getDate());
		cal.add(Calendar.DAY_OF_YEAR, promotion.getDuration());
		cal = endDate(cal);
		return cal.getTime();
	}

	public Calendar endDate(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal;
	}

	@Override
	public Optional<Promotion> get(String id) {
		return promotionRepository.findById(id);
	}
	
}
