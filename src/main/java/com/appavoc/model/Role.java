/** 
 * @class Role
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

package com.appavoc.model;

/*
 * La classe role permet de définir les accés pour chaque utilisateur de l'application
 */

public class Role{

	 enum Role_Admin {CreateOrUpdateOffice, DeleteOffice, CreateOrUpdateAdmin, CreateUser, DeleteUser, GetUserByUsername,
		             GetUserById, deleteAdmin, GetAllAdmin, SearchAdmin, GetMostActiveAdministrator, CreateOrUpdateLawyer, DeleteLawyer, 
		             Role_Lawyer};
	
	 enum Role_Lawyer {CreateOrUpdateAssistant, DeleteAssistant, SearchAssistantByName, CreateOrUpdateBill, DeleteBill, SearchBill, GetTransactionByBillNumber, 
		              CreateOrUpdateCustomer, GetAllCustomer, DeleteCustomer, SearchCustomerByName, CreateOrUpdateDirectory, GetAllDirectory, DeleteDirectory,
		              CreateOrUpdateFolder, GetAllFolder, DeleteFolder, CalculationTimeSpent, CreateOrUpdateDocument, GetAllDocument, DeleteDocument, 
		              SearchDocument, ImportDocument, CreateOrUpdateLegalDir, GetAllLegalDirectory, DeleteLegalDirectory, Role_Assistant};
	
	 enum Role_Assistant {CreateOrUpdateAppointment, GetAllAppointment, CancelAppointment, SearchAppointment, GetAppointmentOfDay, GetAppointmentOfNMonth};
	
	 enum Role_Customer {GetDirectoryOfCustomer, GetAppointmentByCustomerNumber, GetBillsByCustomerNumber, CreateOrUpdateDocument,
		                ImportDocument};

}