package com.erensayar.core.util.service;

import java.io.IOException;

public interface FileUtilService {

  void deleteFileFromHardDrive(String filePath);

  boolean dirExistControl(String path);

  void createDirectory(String path);

  String[] getFileList(String path);

  String createFileFromEncodedStringData(String encodedData, String path, String fileType) throws IOException;
}
