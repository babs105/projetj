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

import com.appavoc.model.Promotion;

public interface PromotionRepository extends MongoRepository<Promotion, String> {

	Promotion findPromotionByPromotionNumber(long promotionNumber);

	void deletePromotionByPromotionNumber(long promotionNumber);
}
