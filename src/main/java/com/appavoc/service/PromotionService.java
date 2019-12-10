/** 
 * @interface PromotionService 
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
import java.util.Optional;

import com.appavoc.model.Promotion;

public interface PromotionService {

	Promotion createOrUpdatePromotion(Promotion promotion);

	Promotion getPromotionByPromotionNumber(long promotionNumber);

	List<Promotion> getAllPromotion();

	void deletePromotion(long promotionNumber);
	
	Date getEndDate(Promotion promotion);
	
	/* methode pour rechercher la promotion pour un abonné */
	Promotion searchPromotionByTransaction(long transactionNumber);
	
	/* methode pour afficher les promotion par type */
	List<Promotion> getPromotionByType(String type);

	/**
	 * @param id
	 * @return
	 */
	Optional<Promotion> get(String id);

}
