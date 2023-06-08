package com.iss.ua.lark.serevice.oss;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: HansonHu
 * @date: 2023-06-08 14:03
 **/
@Configuration
public class AwsS3Config {
    @Value("${aws.s3.accessKeyId}")
    private String accessKeyId;
    @Value("${aws.s3.accessKeySecret}")
    private String accessKeySecret;
    @Value("${aws.s3.regionName}")
    private String regionName;
    @Bean
    public AmazonS3 amazonS3() {
        AWSCredentials awsCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
        AmazonS3ClientBuilder builder = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(awsCredentials));
        //设置S3的地区
        builder.setRegion(regionName);
        AmazonS3 s3Client = builder.build();
        return s3Client;
    }
}
