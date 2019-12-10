package com.appavoc.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.appavoc.service.CloudinaryImageService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;


@Service
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

	private Cloudinary cloudinary;

	@Value("${appavoc.cloudinary.cloud_name}")
	private String cloudName;

	@Value("${appavoc.cloudinary.api_key}")
	private String apiKey;

	@Value("${appavoc.cloudinary.api_secret}")
	private String apiSecret;

	@Value("${appavoc.cloudinary.parent_folder}")
	private String parentFolder;

	private Cloudinary getInstance() {
		if (cloudinary == null) {
			cloudinary = new Cloudinary(
					ObjectUtils.asMap("cloud_name", cloudName, "api_key", apiKey, "api_secret", apiSecret));
		}
		return cloudinary;
	}

	@Override
	public Map uploadImage(MultipartFile file, String filename) throws Exception {

		Map params = ObjectUtils.asMap("public_id", "parentFolder", "overwrite", true,
				"resource_type", "image");
		return getInstance().uploader().upload(file.getBytes(), params);

	}

}
