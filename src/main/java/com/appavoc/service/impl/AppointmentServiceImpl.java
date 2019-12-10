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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.appavoc.model.Appointment;
import com.appavoc.model.TimelineActivity;
import com.appavoc.repository.AppointmentRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.AppointmentService;
import com.appavoc.service.TimelineActivityService;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	final Logger logger = LoggerFactory.getLogger(AppointmentServiceImpl.class);

	@Autowired
	MongoOperations mongo;
	
	@Autowired
	TimelineActivityService timelineActivityService;

	@Autowired
	AppointmentRepository appointmentRepository;

	@Override
	public Appointment createOrUpdateAppointment(Appointment appointment) {
		long appointmentNumber = appointment.getAppointmentNumber();
		Appointment appointStored = getAppointmentByAppointNumber(appointmentNumber);
		if(appointmentNumber == 0 || appointStored == null){
			appointment.setAppointmentNumber(getNextSequence("appointmentNumber"));
		}
		Appointment appointmentCreated = appointmentRepository.save(appointment);
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setType("Appointment");
		timelineActivity.setAction("Create");
		timelineActivity.setElementNumber(appointmentCreated.getAppointmentNumber());
		timelineActivity.setUserId(appointment.getUserId());
		timelineActivityService.createTimelineActivity(timelineActivity);
		
		return appointment;
	}

	@Override
	public Appointment getAppointmentByAppointNumber(long appointmentNumber) {
		Appointment appointment = appointmentRepository.findAppointmentByAppointmentNumber(appointmentNumber);
		return appointment;
	}

	@Override
	public List<Appointment> getAppointmentByLawyerNumber(long lawyerNumber) {
		List<Appointment> appointments = appointmentRepository.findAppointmentByLawyerNumber(lawyerNumber);
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentByCustomerNumber(long customerNumber) {
		List<Appointment> appointments = appointmentRepository.findAppointmentByCustomerNumber(customerNumber);
		return appointments;
	}

	@Override
	public List<Appointment> getAllAppointment() {
		List<Appointment> appointments = appointmentRepository.findAll();
		return appointments;
	}

	@Override
	public void cancelAppointment(long appointmentNumber) {
		Appointment appointmentToDelete = appointmentRepository.findAppointmentByAppointmentNumber(appointmentNumber);
		appointmentRepository.deleteAppointmentByAppointmentNumber(appointmentNumber);
		logger.info("CANCEL SUCCESSFULL");
		
		
		TimelineActivity timelineActivity = new TimelineActivity();
		timelineActivity.setElementNumber(appointmentNumber);
		timelineActivity.setType("Bill");
		timelineActivity.setAction("Appointment");
		timelineActivity.setUserId(appointmentToDelete.getUserId());
		
		timelineActivityService.createTimelineActivity(timelineActivity);
	}

	@Override
	public List<Appointment> searchAppointment(String critere) {
		Criteria criteriaV1 = Criteria.where("place").is(critere);
		Criteria criteriaV2 = Criteria.where("type").is(critere);
		Criteria criteriaV3 = Criteria.where("name").is(critere);
		Query query = new Query(new Criteria().orOperator(criteriaV1, criteriaV2,criteriaV3));
		List<Appointment> appointments = mongo.find(query, Appointment.class);
		return appointments;
	}

	@Override
	public List<Appointment> getCurrentlyAppointment() {

		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Date date = cal.getTime();
		List<Appointment> appointments = appointmentRepository.findAppointmentByAppointmentDate(date);
		return appointments;
	}

	@Override
	public List<Appointment> getAppointmentOfNMonth(int month) {
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
		Criteria criteria1= Criteria.where("appointmentDate").lte(endMonth).gte(startMonth);
		Query query = new Query(criteria1);
		List<Appointment> appointments=mongo.find(query, Appointment.class);
		return appointments;
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
	public List<Appointment> getAppointmentOfDay(String dateStr) {
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
		Criteria criteria1= Criteria.where("appointmentDate").lte(nextDay).gte(previousDay);
		Query query = new Query(criteria1);
		List<Appointment> appointments=mongo.find(query, Appointment.class);
		return appointments;
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
