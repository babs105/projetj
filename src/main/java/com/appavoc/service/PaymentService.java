/** 
 * @interface PaymentService 
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

import com.appavoc.model.Payment;

public interface PaymentService {

	Payment createOrUpdatePayment(Payment payment);

	Payment getPaymentByTransactionId(long transactionId);

	List<Payment> getAllPayment();

	void deletePayment(long transactionId);
	
	/* méthode pour realiser le paiement d'un abonné */
	long payment(long subscriptionNumber);
	
	/* méthode pour realiser le paiement d'un client */
	long paymentCustomer(long customerNumber);

	List<Payment> searchPaymentByName(String critere);
	
	/* methode pour afficher les paiements qui ont reussi */
	List<Payment> getPayed();
	
	/* methode pour afficher les paiements échoués */
	List<Payment> getNoPayed();
	
	/* methode pour afficher les paiements d'une date donné */
	List<Payment> getPaymentOfDay(String dateStr);
	
	/* methode pour afficher les paiements du mois */
	List<Payment> getPaymentOfMonth(int month);

	/**
	 * @param id
	 * @return
	 */
	Optional<Payment> get(String id);
}
