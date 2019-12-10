/** 
 * @interface OfficeService 
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

import com.appavoc.model.Office;

public interface OfficeService {

	Office createOrUpdateOffice(Office office);

	Office getOfficeByOfficeNumber(long officeNumber);

	List<Office> getAllOffice();

	void deleteOffice(long officeNumber);

	List<Office> searchOfficeByName(String critere);

	/* methode pour filtrer les cabinets d'une ville ou d'un pays */
	List<Office> getOfficeByCritere(String critere, List<Office> offices);

	/* methode pour afficher les administrateurs les plus actifs */
	List<Office> getMostActiveOffice();

	/**
	 * @param id
	 * @return
	 */
	Optional<Office> get(String id);

}
