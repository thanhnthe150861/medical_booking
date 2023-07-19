package service;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.Map;


public class AWSS3Client {

    public static final String BUCKET_NAME = "project-swp";
    public static final Region REGION = Region.AP_SOUTHEAST_1;
    public static final String ACCESS_KEY_ID = "AKIA5BAAHJ2XMECKJL4G";
    public static final String SECRET_ACCESS_KEY = "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";
    public static final String KEY = "sexy";
    public  static final String fileSavePath = "E:\\ImageUpload";

    public static void main(String[] args) throws IOException {
        String accessKeyID = ACCESS_KEY_ID;// You get it form AWS Console
        String secretAccessKey = SECRET_ACCESS_KEY; // You get it form AWS Console
        AwsCredentials awsCredentials = new AwsCredentials() {
            @Override
            public String accessKeyId() {
                return accessKeyID;
            }

            @Override
            public String secretAccessKey() {
                return secretAccessKey;
            }
        };
        AwsCredentialsProvider awsCredentialsProvider = new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return awsCredentials;
            }
        };

        S3Client s3Client = S3Client.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
        S3Presigner s3Presigner = S3Presigner.builder().region(REGION).credentialsProvider(awsCredentialsProvider).build();
        //Put object to S3
        putObject(
                s3Client, BUCKET_NAME, KEY,
                Files.readAllBytes(Paths.get("E:\\ImageUpload\\anh-gai-xinh-03.jpg")));

        //Gen presignUrl
        var request =
                GetObjectPresignRequest.builder()
                        .signatureDuration(Duration.ZERO)
                        .getObjectRequest(d -> d.bucket(BUCKET_NAME).key(KEY))
                        .build();
        String presignUrl = s3Presigner.presignGetObject(request).url().toString();
        System.out.println("url:" + presignUrl);
    }

    public static String putObject(S3Client s3Client, String bucket, String keyName, byte[] data) {
        PutObjectRequest objectRequest = (PutObjectRequest) PutObjectRequest.builder().bucket(bucket).key(keyName).build();
        PutObjectResponse response = s3Client.putObject(objectRequest, RequestBody.fromBytes(data));
        return response.eTag();
    }

    public static String putObject(S3Client s3Client, String bucket, String keyName, File file) {
        PutObjectRequest objectRequest = (PutObjectRequest) PutObjectRequest.builder().bucket(bucket).key(keyName).metadata((Map) null).build();
        PutObjectResponse response = s3Client.putObject(objectRequest, RequestBody.fromFile(file));
        return response.eTag();
    }

    public static byte[] getObject(S3Client s3Client, String bucket, String keyName) {
        ResponseBytes<GetObjectResponse> s3Object = (ResponseBytes) s3Client.getObject((GetObjectRequest) GetObjectRequest.builder().bucket(bucket).key(keyName).build(), ResponseTransformer.toBytes());
        byte[] bytes = s3Object.asByteArray();
        return bytes;
    }
}