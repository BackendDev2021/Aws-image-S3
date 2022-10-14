package com.Aws.s3.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Aws.s3.service.S3BucketStorageService;
import com.amazonaws.services.s3.model.ObjectMetadata;

@RestController
public class S3BucketStorageController {

	@Autowired
	S3BucketStorageService service;

	@PostMapping("/file/upload")
	public ObjectMetadata uploadFile(@RequestParam String fileName, MultipartFile file)
			throws Exception {
		return service.uploadFile(fileName, file);
	}
}
