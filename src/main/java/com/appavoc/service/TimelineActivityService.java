package com.appavoc.service;

import java.util.List;

import com.appavoc.model.TimelineActivity;

public  interface TimelineActivityService {
	
	TimelineActivity createTimelineActivity(TimelineActivity timelineActivity);
	
	List<TimelineActivity> getTimelineActivityOfUser(String userId);
	List<TimelineActivity> getAllTimelineActivity();
	void deleteTimeLineActivity(long timelineActivityNumber);
}
