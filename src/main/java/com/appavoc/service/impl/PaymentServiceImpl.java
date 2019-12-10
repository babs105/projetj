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
import java.util.Optional;
import java.util.TimeZone;

import com.appavoc.repository.PaymentRepository;
import com.appavoc.model.Payment;
import com.appavoc.model.TimelineActivity;
import com.appavoc.service.PaymentService;
import com.appavoc.service.TimelineActivityService;
import com.appavoc.repository.sequence.SequenceId;

@Service
public class PaymentServiceImpl implements PaymentService{

	final Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	TimelineActivityService timelineActivityService;

	@Autowired
	private MongoOperations mongo;

	@Override
	public Payment createOrUpdatePayment(Payment payment) {
		long paymentNumber = payment.getTransactionId();
		Payment paymentStored = getPaymentByTransactionId(paymentNumber);
		if(paymentNumber == 0 || paymentStored == null){
			payment.setTransactionId(getNextSequence("transactionId"));
			Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
			Date paymentDate = cal.getTime();
			payment.setTransactionDate(paymentDate);
		}
		Payment paymentCreated = paymentRepository.save(payment);
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Payment");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(paymentCreated.getPackNumber());
		timelineActivity.setUserId(payment.getUserId());
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return payment;
	}

	@Override
	public Payment getPaymentByTransactionId(long transactionId) {
		Payment payment = paymentRepository.findPaymentByTransactionId(transactionId);
		return payment;
	}

	@Override
	public List<Payment> getAllPayment() {
		List<Payment> payments = paymentRepository.findAll();
		return payments;
	}

	@Override
	public void deletePayment(long transactionId) {
		paymentRepository.deletePaymentByTransactionId(transactionId);
		logger.info("DELETE SUCCESSFULL");
	}

	@Override
	public long payment(long subscriptionNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long paymentCustomer(long customerNumber) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Payment> searchPaymentByName(String critere) {
		Criteria criteriaV1 = Criteria.where("firstName").is(critere);
		Criteria criteriaV2 = Criteria.where("lastName").is(critere);
		Criteria criteriaV3 = Criteria.where("name").is(critere);
		Query query = new Query(new Criteria().orOperator(criteriaV1, criteriaV2,criteriaV3));
		List<Payment> payments = mongo.find(query, Payment.class);
		return payments;
	}

	@Override
	public List<Payment> getPayed() {
		Query query = new Query(Criteria.where("payed").is(true));
		List<Payment> payments = mongo.find(query, Payment.class);
		return payments;
	}

	@Override
	public List<Payment> getNoPayed() {
		Query query = new Query(Criteria.where("payed").is(false));
		List<Payment> payments = mongo.find(query, Payment.class);
		return payments;
	}

	@Override
	public List<Payment> getPaymentOfDay(String dateStr) {
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
		Criteria criteria1= Criteria.where("transactionDate").lte(nextDay).gte(previousDay);
		Query query = new Query(criteria1);
		List<Payment> payments=mongo.find(query, Payment.class);
		return payments;
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
	public List<Payment> getPaymentOfMonth(int month) {
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		logger.info("Date avant ="+cal.getTime());
		cal.add(Calendar.MONTH, month);
		logger.info("Date apres ="+cal.getTime());
		int nbrDaysOfMonths = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		logger.info("\n\n nbrDaysOfMonths  " +nbrDaysOfMonths);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal = startDate(cal);
		Calendar startMonthCal = cal;
		Date startMonth = startMonthCal.getTime();
		cal.set(Calendar.DAY_OF_MONTH, nbrDaysOfMonths);
		cal = endDate(cal);
		Date endMonth = cal.getTime();
		logger.info("startDate="+startMonth + " \n\n endMonth= " +endMonth);
		Criteria criteria1= Criteria.where("billDate").lte(endMonth).gte(startMonth);
		Query query = new Query(criteria1);
		List<Payment> payments=mongo.find(query, Payment.class);
		return payments;
	}

	@Override
	public Optional<Payment> get(String id) {
		return paymentRepository.findById(id);
	}

}
