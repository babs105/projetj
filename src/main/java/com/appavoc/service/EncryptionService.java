/** 
 * @interface EncryptionService 
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

public interface EncryptionService {

	String encrypt(String value) throws Exception;

	String decrypt(String encrypted) throws Exception;

	String getSaltedHash(String password) throws Exception;

	boolean check(String password, String stored) throws Exception;

}
