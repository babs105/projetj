package com.appavoc.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.appavoc.model.Bill;
import com.appavoc.model.TimelineActivity;
import com.appavoc.service.TimelineActivityService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/timeLine")
public class TimelineActivityController {
	
	@Autowired
    TimelineActivityService timelineActivityService;
	
	
	
	@RequestMapping(value = "/create", method = {RequestMethod.POST, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public  TimelineActivity createOrUpdateTimelineActivity(@RequestBody @Valid TimelineActivity timelineActivity) {
		return timelineActivityService.createTimelineActivity(timelineActivity);
	}
	
	@RequestMapping(value = "/getTimelineActivityByUser/{userId}", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	public List<TimelineActivity> getTimelineActivityOfUser(@PathVariable String userId)  {
	
		return timelineActivityService.getTimelineActivityOfUser(userId);
	}

	
	@RequestMapping(value = "/getAllTimelineActivity", method = {RequestMethod.GET, RequestMethod.OPTIONS }, produces = "application/json")
	@ResponseBody
	//@Secured ({"Role_Lawyer"})
	public List<TimelineActivity> AllTimelineActivity() {
		return timelineActivityService.getAllTimelineActivity();
	}
}
