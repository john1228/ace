package com.ace.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public enum Aliyun {
    Instance;
    private OSS client;

    Aliyun() {
        client = new OSSClientBuilder().build("http://oss-cn-hangzhou.aliyuncs.com", "123", "132");
    }


    public String upload(String bucketName, MultipartFile file) throws IOException {
        String filePath = new SimpleDateFormat("/yyyy/MM/dd").format(new Date());
        String tmpBkt = bucketName + filePath;
        if (!client.doesBucketExist(tmpBkt)) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucketName);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            client.createBucket(createBucketRequest);
        }
        //生产key
        String fileName = UUID.randomUUID().toString();
        client.putObject(new PutObjectRequest(tmpBkt, fileName, file.getInputStream()));
        return bucketName + filePath + fileName;
    }
}
