package com.ace.util;

import com.aliyun.oss.OSSClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public enum Aliyun {
    Instance;
    private static final String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
    private static final String accessKeyId = "<yourAccessKeyId>";
    private static final String accessKeySecret = "<yourAccessKeySecret>";
    private static final String bucketName = "<yourBucketName>";
    private static final String objectName = "<yourObjectName>";

    public void upload(File file) throws FileNotFoundException {
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        FileInputStream inputStream = new FileInputStream(file);
        ossClient.putObject(bucketName, objectName, inputStream);
        ossClient.shutdown();
    }
}
