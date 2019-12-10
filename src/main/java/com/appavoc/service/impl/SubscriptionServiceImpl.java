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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.appavoc.repository.SubscriptionRepository;
import com.appavoc.model.Subscription;
import com.appavoc.service.SubscriptionService;
import com.appavoc.repository.sequence.SequenceId;

@Service
public class SubscriptionServiceImpl implements SubscriptionService {

	final Logger logger = LoggerFactory.getLogger(SubscriptionServiceImpl.class);

	@Autowired
	MongoOperations mongo;

	@Autowired
	SubscriptionRepository subscriptionRepository;

	@Override
	public Subscription createOrUpdateSubscription(Subscription subscription) {
		long subscriptionNumber = subscription.getSubscriptionNumber();
		Subscription subscriptionStored = getSubscriptionBySubscriptionNumber(subscriptionNumber);
		if(subscriptionNumber == 0 || subscriptionStored == null){
			subscription.setSubscriptionNumber(getNextSequence("subscriptionNumber"));
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			Date date = cal.getTime();
			subscription.setDate(date);
		}
		subscriptionRepository.save(subscription);
		return subscription;
	}

	@Override
	public Subscription getSubscriptionBySubscriptionNumber(long subscriptionNumber) {
		Subscription subscription = subscriptionRepository.findSubscriptionBySubscriptionNumber(subscriptionNumber);
		return subscription;
	}

	@Override
	public List<Subscription> getAllSubscription() {
		List<Subscription> subscriptions = subscriptionRepository.findAll();
		return subscriptions;
	}

	@Override
	public void deleteSubscription(long subscriptionNumber) {
		subscriptionRepository.deleteSubscriptionBySubscriptionNumber(subscriptionNumber);
		logger.info("DELETE SUCCESSFULL");
	}

	@Override
	public boolean ValidationSubscription(long transactionNumber) {
		if(transactionNumber != 0)
			return true;
		return false;
	}

	@Override
	public Subscription searchSubscriptionByOffice(long officeNumber) {
		Query query = new Query(Criteria.where("officeNumber").is(officeNumber));
		Subscription subscription = mongo.findOne(query, Subscription.class);
		return subscription;
	}

	@Override
	public List<Subscription> getSubscriptionsOfPack(String packType) {

		Query query = new Query(Criteria.where("officeNumber").is(packType));
		List<Subscription> subscriptions = mongo.find(query, Subscription.class);
		return subscriptions;
	}

	@Override
	public List<Subscription> getSubscriptionsOfDay(String dateStr) {
		Date date = getDate(dateStr);
		Calendar previousDayCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Calendar nextDayCal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		previousDayCal.setTime(date);
		logger.info("Date precedent avant ="+previousDayCal.getTime());
		nextDayCal.setTime(date);
		logger.info("Date suivant avant ="+nextDayCal.getTime());
		previousDayCal.add(Calendar.DAY_OF_MONTH, -1);
		previousDayCal = endDate(previousDayCal);
		Date previousDay= previousDayCal.getTime();
		logger.info("Date precedent apres ="+previousDay);
		nextDayCal.add(Calendar.DAY_OF_MONTH, 1);
		nextDayCal = startDate(nextDayCal);
		Date nextDay = nextDayCal.getTime();
		logger.info("Date suivant apres ="+nextDay);
		Criteria criteria1= Criteria.where("date").lte(nextDay).gte(previousDay);
		Query query = new Query(criteria1);
		List<Subscription> subscription=mongo.find(query, Subscription.class);
		return subscription;
	}

	@Override
	public List<Subscription> getSubscriptionsByDuration(int duration) {
		Query query = new Query(Criteria.where("duration").is(duration));
		List<Subscription> subscriptions = mongo.find(query, Subscription.class);
		return subscriptions;
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

	public Date getDate(String dateStr) {
		System.out.println("avant conversion");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date date = formatter.parse(dateStr);
			System.out.println("aprés conversion");
			return date;
		} catch (ParseException e) {                
			return null;
		}
	}

	public Calendar startDate(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	public Calendar endDate(Calendar cal){
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		cal.set(Calendar.MILLISECOND, 999);
		return cal;
	}

	@Override
	public Date getEndOfSubscription(Subscription subscription) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		cal.setTime(subscription.getDate());
		cal.add(Calendar.DAY_OF_YEAR, subscription.getDuration());
		cal = endDate(cal);
		return cal.getTime();
	}

}
