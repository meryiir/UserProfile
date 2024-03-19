package com.amigoscode.ansimageupload.bucket;

public enum BucketName {
    PROFILE_IMAGE("amigoscode");/*name or image of the bucket created in amazon s3 client*/
    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;

    }

    public String getBucketName() {

        return bucketName;
    }
}
