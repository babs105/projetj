/** 
 * @interface AssistantService 
 */

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

package com.appavoc.service;

import java.util.List;

import com.appavoc.model.Assistant;

public interface AssistantService {

	Assistant createOrUpdateAssistant(Assistant assistant);

	Assistant getAssistantByAssistantNumber(long assistantNumber);

	List<Assistant> getAllAssistant();

	void deleteAssistant(long assistantNumber);
	
	List<Assistant> searchAssistantByName(String critere);
	
	List<Assistant> getMostActiveAssistant();

}
