package com.Aws.s3.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class S3BucketStorageService {

	@Autowired
	private AmazonS3 amazonS3Client;

	@Value("${aws.s3bucket.name}")
	private String bucketName;

	public ObjectMetadata uploadFile(String fileName, MultipartFile file) throws Exception {
		String bucketName = "file-bucket";
		if(amazonS3Client.doesBucketExist(bucketName)) {
		    log.warn("Bucket name is not available.Try again with a different Bucket name");
		}
		amazonS3Client.createBucket(bucketName);
		
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentLength(file.getSize());
		return  amazonS3Client.putObject(bucketName, fileName, file.getInputStream(), metadata).getMetadata();
	}
}
