package com.amigoscode.ansimageupload.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    @Bean
    public AmazonS3 s3 () {
        AWSCredentials awsCredentials = new BasicAWSCredentials(
               "AKIA2UC3D2PYGOYYA4ZX",
                "meSZH3YlYY0TfZy6l4Hj2hhL4DWxmRF1XDMVFu1X"

        );
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();
    }/*to inject in other classes*/
}
