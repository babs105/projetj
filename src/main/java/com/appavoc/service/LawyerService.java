/** 
 * @interface LawyerService 
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

import javax.validation.Valid;

import com.appavoc.baseDTO.OfficeRequestDTO;
import com.appavoc.baseDTO.OfficeResponseDTO;
import com.appavoc.model.Lawyer;

public interface LawyerService {

	Lawyer createOrUpdateLawyer(Lawyer lawyer);

	Lawyer getLawyerByLawyerNumber(long lawyerNumber);

	List<Lawyer> getAllLawyer();

	void deleteLawyer(long lawyerNumber);

	List<Lawyer> searchLawyerByName(String critere);
	
	List<Lawyer> getMostActiveLawyer();
	
	List<Lawyer> getLawyersByOfficeNumber(long officeNumber);

	/**
	 * @param id
	 * @return
	 */
	Optional<Lawyer> get(String id);

	OfficeResponseDTO createLawyerRegistration(OfficeRequestDTO officeRequestDto) throws Exception;

}
