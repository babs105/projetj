package com.appavoc.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;


import com.appavoc.model.TimelineActivity;

public interface TimelineActivityRepository  extends MongoRepository<TimelineActivity,String> {

	List<TimelineActivity> findAllTimelineActivityByUserId(String userId);
	
	TimelineActivity findTimelineByTimelineActivityNumber(long timelineActivityNumber);
	
	List<TimelineActivity> findAll();
	
	void deleteTimelineActivityByTimelineActivityNumber(long timelineActivityNumber);
}
