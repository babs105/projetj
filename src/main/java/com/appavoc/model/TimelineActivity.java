package com.appavoc.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
public class TimelineActivity {

	@Id
	private String id = UUID.randomUUID().toString();
	
	private long timelineActivityNumber;
	
	private String action;
	
	private Date date;
	
	private String type;
	
	private String userId;
	
	private long elementNumber;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	
	
	public long getElementNumber() {
		return elementNumber;
	}

	public void setElementNumber(long elementNumber) {
		this.elementNumber = elementNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public long getTimelineActivityNumber() {
		return timelineActivityNumber;
	}

	public void setTimelineActivityNumber(long timelineActivityNumber) {
		this.timelineActivityNumber = timelineActivityNumber;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	
}
