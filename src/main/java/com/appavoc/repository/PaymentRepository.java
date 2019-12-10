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

import com.appavoc.model.Payment;

public interface PaymentRepository extends MongoRepository<Payment, String> {

	Payment findPaymentByTransactionId(long transactionId);

	void deletePaymentByTransactionId(long transactionId);
}
