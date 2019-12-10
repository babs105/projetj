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

import com.appavoc.model.Subscription;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

	Subscription findSubscriptionBySubscriptionNumber(long subscriptionNumber);

	void deleteSubscriptionBySubscriptionNumber(long subscriptionNumber);

}
