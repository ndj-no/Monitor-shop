package com.mshop.fileservice.service;

public interface FileService {

    String saveFileString(String base64) throws Exception;

    String readFileString(String key) throws Exception;

    void deleteFile(String key) throws Exception;

}
