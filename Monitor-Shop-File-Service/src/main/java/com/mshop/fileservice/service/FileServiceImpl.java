package com.mshop.fileservice.service;

import io.minio.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService {

    private final MinioClient minioClient;

    @Value("${storage.minio.bucket}")
    private String bucket;

    @Override
    public String saveFileString(String base64) throws Exception {
        byte[] bytes = base64.getBytes();
        try (InputStream inputStream = new ByteArrayInputStream(bytes)) {
            PutObjectArgs args = PutObjectArgs.builder()
                    .bucket(bucket)
                    .object(LocalDate.now() + "/" + LocalTime.now().format(DateTimeFormatter.ofPattern("hh_mm_ss")))
                    .stream(inputStream, bytes.length, -1)
                    .build();
            ObjectWriteResponse response = minioClient.putObject(args);
            inputStream.close();
            return response.object();
        }
    }

    @Override
    public String readFileString(String key) throws Exception {
        try (InputStream stream = minioClient.getObject(
                GetObjectArgs
                        .builder()
                        .bucket(bucket)
                        .object(key)
                        .build())) {
            byte[] bytes = stream.readAllBytes();
            return new String(bytes);
        }
    }

    @Override
    public void deleteFile(String key) throws Exception {
        minioClient.removeObject(
                RemoveObjectArgs.builder()
                        .bucket(bucket)
                        .object(key)
                        .build()
        );
    }

}
