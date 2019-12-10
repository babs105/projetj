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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import com.appavoc.model.Bill;
import com.appavoc.model.Folder;
import com.appavoc.model.TimelineActivity;
import com.appavoc.repository.BillRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.BillService;
import com.appavoc.service.FolderService;
import com.appavoc.service.TimelineActivityService;

@Service
public class BillServiceImpl implements BillService {

	final Logger logger = LoggerFactory.getLogger(BillServiceImpl.class);

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	TimelineActivityService timelineActivityService;

	@Autowired
	private MongoOperations mongo;
	
	@Autowired
	FolderService folderService;

	@Override
	public Bill createOrUpdateBill(Bill bill) {
		long billNumber = bill.getBillNumber();
		Bill billStored = getBillByBillNumber(billNumber);
		
		  if(billNumber == 0 || billStored == null){
			  bill.setBillNumber(getNextSequence("billNumber")); Calendar cal =
			  Calendar.getInstance(TimeZone.getTimeZone("UTC")); Date date = cal.getTime();
			  bill.setBillDate(date); 
		}
		 
		Bill billCreated = billRepository.save(bill);
		 
		long folderNumber=bill.getFolderNumber();
		String userId = folderService.getFolderByFolderNumber(folderNumber).getUserId();
		
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Bill");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(billCreated.getBillNumber());
		timelineActivity.setUserId(userId);
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return bill;
	}

	@Override
	public Bill getBillByBillNumber(long billNumber) {
		Bill bill = billRepository.findBillByBillNumber(billNumber);
		return bill;
	}

	@Override
	public List<Bill> getAllBill() {
		List<Bill> bills = billRepository.findAll();
		return bills;
	}

	@Override
	public void deleteBill(long billNumber) {
		Bill billToDelete = billRepository.findBillByBillNumber(billNumber);
		
		billRepository.deleteBillByBillNumber(billNumber);
		
		logger.info("DELETE SUCCESSFULL");
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(billNumber);
		timelineActivity.setType("Bill");
		timelineActivity.setAction("Delete");
		
		timelineActivity.setUserId(folderService.getFolderByFolderNumber(billToDelete.getFolderNumber()).getUserId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
	}

	@Override
	public Bill calculateBill(long billNumber) {
		
		Bill bill = getBillByBillNumber(billNumber);
		long folderNumber= getFolderByBillNumber(billNumber);
		Folder folder = folderService.getFolderByFolderNumber(folderNumber);
		double tva = bill.getMinutePrice() * folder.getTimeSpend();
		bill.setSubTotal(bill.getMinutePrice() * folder.getTimeSpend() );
		bill.setBillAmount(bill.getSubTotal() + bill.getSubTotal() * tva);
		bill = createOrUpdateBill(bill);
		
		String userId = folder.getUserId();
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Bill");
		timelineActivity.setAction("calculateBill");
		timelineActivity.setElementNumber(billNumber);
		timelineActivity.setUserId(userId);
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return bill;
	}

	@Override
	public List<Bill> getRecentlyBill() {
		Query query = new Query();
		query.limit(10);
		query.with(new Sort(Sort.Direction.DESC, "billDate"));
		List<Bill> bills = mongo.find(query, Bill.class);
		return bills;
	}

	@Override
	public List<Bill> getBillsByCustomerNumber(long customerNumber) {
		List<Bill> bills = billRepository.findAllBillByCustomerNumber(customerNumber);
		return bills;
	}

	@Override
	public List<Bill> getBillsByOfficeNumber(long officeNumber) {
		List<Bill> bills = billRepository.findAllBillByOfficeNumber(officeNumber);
		return bills;
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
	public long getTransactionByBillNumber(long billNumber) {
		Bill bill = billRepository.findBillByBillNumber(billNumber);
		return bill.getTransactionNumber();
	}

	@Override
	public List<Bill> getBillsByLawyerNumber(long lawyerNumber) {
		List<Bill> bills = billRepository.findAllBillByLawyerNumber(lawyerNumber);
		return bills;
	}

	@Override
	public long getFolderByBillNumber(long billNumber) {
		Bill bill = billRepository.findBillByBillNumber(billNumber);
		return bill.getFolderNumber();
	}

	@Override
	public List<Bill> searchBill(String dateStr) {
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
		Criteria criteria1= Criteria.where("billDate").lte(nextDay).gte(previousDay);
		Criteria criteria2= Criteria.where("deadline").lte(nextDay).gte(previousDay);
		Query query = new Query(new Criteria().orOperator(criteria1, criteria2));
		List<Bill> bills=mongo.find(query, Bill.class);
		return bills;
	}

	@Override
	public List<Bill> searchBillOfMonth(int month) {
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
		List<Bill> bills=mongo.find(query, Bill.class);
		return bills;
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


}
