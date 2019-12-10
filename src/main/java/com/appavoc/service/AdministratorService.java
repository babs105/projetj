/** 
 * @interface AdministratorService 
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

import com.appavoc.model.Administrator;

public interface AdministratorService {

	/* methode pour creer ou mettre à jour un administrateur*/
	Administrator createOrUpdateAdmin(Administrator administrator);

	/* methode pour afficher un administrateur grace à son attribut adminNumber*/
	Administrator getAdminByAdminNumber(long adminNumber);

	/* methode pour supprimer un administrateur grace à son attribut adminNumber*/
	void deleteAdmin(long adminNumber);
	
	/* methode pour afficher tous les administrateurs*/
	List<Administrator> getAllAdmin();
	
	/* methode pour rechercher un administrateur via son nom ou son prenom*/
	List<Administrator> searchAdmin(String critere);
	
	/* methode pour avoir les administrateurs les plus actifs*/
	List<Administrator> getMostActiveAdministrator();
	
} 
