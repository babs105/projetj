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

package com.appavoc.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.appavoc.model.Assistant;

public interface AssistantRepository extends MongoRepository<Assistant, String> {

	Assistant findAssistantByAssistantNumber(long assistantNumber);

	void deleteAssistantByAssistantNumber(long assistantNumber);

}
