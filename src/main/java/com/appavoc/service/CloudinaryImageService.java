/** 
 * @interface CloudinaryImageService 
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

import java.io.IOException;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageService {

	public Map uploadImage(MultipartFile multipartFile, String filename) throws IOException, Exception;

}
