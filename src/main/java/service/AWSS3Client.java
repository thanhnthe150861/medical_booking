package service;

import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.model.S3Exception;
import java.io.File;
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
}