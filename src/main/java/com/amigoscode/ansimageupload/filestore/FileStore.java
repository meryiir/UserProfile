package com.amigoscode.ansimageupload.filestore;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.IIOException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Optional;
import java.util.function.Supplier;

@Service
public class FileStore {

    // This is where we store the AmazonS3 object for interacting with S3 storage
    private final AmazonS3 s3;

    // This is a constructor. It sets up the FileStore with an AmazonS3 object.
    @Autowired
    public FileStore(AmazonS3 s3) {
        this.s3 = s3;
    }

    // This method is for saving a file to S3.
    public void save(String path,
                     String fileName,
                     Optional<Map<String, String>> optionalMetadata,
                     InputStream inputStream) {
        // We create a metadata object to hold extra information about the file.
        ObjectMetadata metadata = new ObjectMetadata();

        // If there's optional metadata, we add it to the metadata object.
        optionalMetadata.ifPresent(map -> {
            if (!map.isEmpty()) {
                map.forEach(metadata::addUserMetadata);
            }
        });

        try {
            // We try to upload the file to the S3 bucket with the given path, filename, input stream, and metadata.
            s3.putObject(path, fileName, inputStream, metadata);
        } catch (AmazonServiceException e) {
            // If something goes wrong during the upload, we handle the error and throw an IllegalStateException.
            throw new IllegalStateException("Failed to store file to s3", e);
        }
    }
}

