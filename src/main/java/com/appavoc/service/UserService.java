/** 
 * @interface UserService 
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

import com.appavoc.model.User;

public interface UserService{

    User getUserByUsername(String username);

    User createUser(User user) ;
    
    User getUserById(String id);
    
    void deleteUser(String id);
    
}
