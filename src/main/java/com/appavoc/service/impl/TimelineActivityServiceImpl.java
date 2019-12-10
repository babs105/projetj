package com.appavoc.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;


import com.appavoc.model.TimelineActivity;
import com.appavoc.repository.TimelineActivityRepository;
import com.appavoc.repository.sequence.SequenceId;
import com.appavoc.service.TimelineActivityService;


@Service
public class TimelineActivityServiceImpl implements TimelineActivityService {
	
	@Autowired
	TimelineActivityRepository timelineActivityRepository;
	
	@Autowired
	private MongoOperations mongo;
	
	@Override
	public TimelineActivity createTimelineActivity(TimelineActivity timelineActivity) {
		// TODO Auto-generated method stub
		long timelineActivityNumber = timelineActivity.getTimelineActivityNumber();
		TimelineActivity timelineActivityStored = getTimelineActivityByTimelineActivityNumber(timelineActivityNumber);
      
	    Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
		Date date = cal.getTime();
		if(timelineActivityNumber == 0 || timelineActivityStored == null ) {
			
		   timelineActivity.setTimelineActivityNumber(getNextSequence("timelineActivityNumber"));
		}
		timelineActivity.setDate(date);
		timelineActivityRepository.save(timelineActivity);
		return timelineActivity;
	}

	private TimelineActivity getTimelineActivityByTimelineActivityNumber(long timelineActivityNumber) {
		// TODO Auto-generated method stub
		TimelineActivity timelineActivity = timelineActivityRepository.findTimelineByTimelineActivityNumber(timelineActivityNumber);
		return timelineActivity;
	}

	@Override
	public List<TimelineActivity> getTimelineActivityOfUser(String userId) {
		List<TimelineActivity> activitiesByUser = timelineActivityRepository.findAllTimelineActivityByUserId(userId);
		return activitiesByUser ;
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
	public void deleteTimeLineActivity(long timelineActivityNumber) {
		// TODO Auto-generated method stub
		timelineActivityRepository.deleteTimelineActivityByTimelineActivityNumber(timelineActivityNumber);
		
	}

	@Override
	public List<TimelineActivity> getAllTimelineActivity() {
		// TODO Auto-generated method stub
		List<TimelineActivity> allActivities = timelineActivityRepository.findAll();
		return  allActivities;
				
	}
}
