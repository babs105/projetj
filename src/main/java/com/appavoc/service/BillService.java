/** 
 * @interface BillService 
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

import com.appavoc.model.Bill;

public interface BillService {

	Bill createOrUpdateBill(Bill bill);

	Bill getBillByBillNumber(long billNumber);

	List<Bill> getAllBill();

	void deleteBill(long billNumber);
	
	/* methode pour calculer le montant de la facture */
	Bill calculateBill(long billNumber);
	
	/* methode pour afficher les factures les plus récentes */
	List<Bill> getRecentlyBill();
	
	List<Bill> searchBill(String dateStr);
	
	/* methode pour rechercher les factures du mois */
	List<Bill> searchBillOfMonth(int month);

	/* methode pour afficher les factures d'un client donné */
	List<Bill> getBillsByCustomerNumber(long customerNumber); 
	
	/* methode pour afficher les factures d'un cabinet donné */
	List<Bill> getBillsByOfficeNumber(long officeNumber); 
	
	/* methode pour afficher les factures d'un avocat donné */
	List<Bill> getBillsByLawyerNumber(long lawyerNumber);
	
	long getTransactionByBillNumber(long billNumber);
	
	/* methode pour recupérer le dossier d'une facture donnée */
	long getFolderByBillNumber(long billNumber);
	
}
