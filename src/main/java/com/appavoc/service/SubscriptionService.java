/** 
 * @interface SubscriptionService 
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

import java.util.Date;
import java.util.List;

import com.appavoc.model.Subscription;

public interface SubscriptionService {

	Subscription createOrUpdateSubscription(Subscription subscription);

	Subscription getSubscriptionBySubscriptionNumber(long subscriptionNumber);

	List<Subscription> getAllSubscription();

	void deleteSubscription(long subscriptionNumber);
	
	/* methode pour valider un abonnement */
	boolean ValidationSubscription(long transactionNumber);
	
	/* methode pour rechercher l'abonnement d'un cabinet */
	Subscription searchSubscriptionByOffice(long officeNumber);
	
	/* methode pour afficher les abonnées d'un pack définis */
	List<Subscription> getSubscriptionsOfPack(String packType);
	
	/* methode pour afficher les abonnés du jour */
	List<Subscription> getSubscriptionsOfDay(String dateStr);
	
	/* methode pour afficher les abonnés par durée */
	List<Subscription> getSubscriptionsByDuration(int duration);
	
	Date getEndOfSubscription(Subscription subscription);
	
} 
