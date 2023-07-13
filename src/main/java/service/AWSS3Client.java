package service;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.File;
import java.util.Map;

public class AWSS3Client {
    private static final String BUCKET_NAME = "project-swp";
    private static final Region REGION = Region.AP_SOUTHEAST_1;
    public static void main(String[] args) {
        S3Client s3Client = S3Client.builder()
                .region(REGION)
                .credentialsProvider(DefaultCredentialsProvider.create())
                .build();
        File file = new File("path/to/image.jpg"); // Replace with the actual path to your image file
        String key = "images/" + file.getName(); // Set the desired key for the uploaded file
        try {
            PutObjectResponse response = s3Client.putObject(PutObjectRequest.builder()
                    .bucket(BUCKET_NAME)
                    .key(key)
                    .build(), file.toPath());
            String imageUrl = "https://" + BUCKET_NAME + ".s3." + REGION + ".amazonaws.com/" + key;
            System.out.println("Image uploaded successfully. URL: " + imageUrl);
        } catch (S3Exception e) {
            System.err.println("Error uploading image to S3: " + e.getMessage());
        }
    }

    private static String putObject(S3Client s3Client, String bucket, String keyName, byte[] data) {
        PutObjectRequest objectRequest = (PutObjectRequest) PutObjectRequest.builder().bucket(bucket).key(keyName).build();
        PutObjectResponse response = s3Client.putObject(objectRequest, RequestBody.fromBytes(data));
        return response.eTag();
    }

    private static String putObject(S3Client s3Client, String bucket, String keyName, File file) {
        PutObjectRequest objectRequest = (PutObjectRequest) PutObjectRequest.builder().bucket(bucket).key(keyName).metadata((Map) null).build();
        PutObjectResponse response = s3Client.putObject(objectRequest, RequestBody.fromFile(file));
        return response.eTag();
    }

    private static byte[] getObject(S3Client s3Client, String bucket, String keyName) {
        ResponseBytes<GetObjectResponse> s3Object = (ResponseBytes) s3Client.getObject((GetObjectRequest) GetObjectRequest.builder().bucket(bucket).key(keyName).build(), ResponseTransformer.toBytes());
        byte[] bytes = s3Object.asByteArray();
        return bytes;
    }

}

