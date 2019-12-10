/** 
 * @interface PackService 
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
import java.util.Optional;

import com.appavoc.model.Pack;

public interface PackService {

	Pack createOrUpdatePack(Pack pack);

	Pack getPackByPackNumber(long packNumber);

	List<Pack> getAllPack();

	void deletePack(long packNumber);
	
	/* methode pour calculer le montant d'un pack en fonction du nombre d'utilisateur */
	Pack calculatePrice(int numberOfUsers);
	
	/* methode pour rechercher un pack à travers le nombre d'utilisateur */
	Pack searchPackByNumberOfUsers(int numberOfUsers);

	/**
	 * @param id
	 * @return
	 */
	Optional<Pack> get(String id);

}
