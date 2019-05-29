package com.ace.util;


import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.CreateBucketRequest;
import com.aliyun.oss.model.PutObjectRequest;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public enum Aliyun {
    Instance;
    private OSS client;
    private final String bucket = "bb-platform";
    public final String endPoint = "http://oss-cn-hangzhou.aliyuncs.com";
    public final String imgHost = "https://bb-platform.oss-cn-hangzhou.aliyuncs.com/";
    private final String accessKey = "LTAIbq6LWRQxDB88";
    private final String secretKey = "QNdPhGUuSNGhV2pDFcPu9L3pBg8aPY";

    Aliyun() {
        client = new OSSClientBuilder().build(endPoint, accessKey, secretKey);
    }


    public String upload(MultipartFile file) throws IOException {
        String filePath = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        String fileName = UUID.randomUUID().toString();
        String extension = FilenameUtils.getExtension(file.getOriginalFilename());
        if (!client.doesBucketExist(bucket)) {
            CreateBucketRequest createBucketRequest = new CreateBucketRequest(bucket);
            createBucketRequest.setCannedACL(CannedAccessControlList.PublicRead);
            client.createBucket(createBucketRequest);
        }
        client.putObject(new PutObjectRequest(bucket, filePath + fileName + "." + extension, file.getInputStream()));
        return filePath + fileName + "." + extension;
    }

    public static String scale(String imgPath, int h, int w) {
        return String.format("%s?x-oss-process=image/resize,m_fill,h_%d,w_%d", imgPath, h, w);
    }
}
