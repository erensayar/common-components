package com.erensayar.core.util.service.implementation;

import com.erensayar.core.error.exception.BadRequestException;
import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.util.service.FileUtilService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class FileUtilServiceImpl implements FileUtilService {

  @Override
  public void deleteFileFromHardDrive(String filePath) {
    Path path = Paths.get(filePath);
    try {
      Files.deleteIfExists(path);
    } catch (IOException e) {
      throw new BadRequestException(LogModel.builder()
          .javaExceptionName(e.getClass().getSimpleName())
          .javaExceptionMessage(e.getMessage())
          .build());
    }
  }

  @Override
  public boolean dirExistControl(String path) {
    Path target = Paths.get(path);
    return Files.isDirectory(target);
  }

  @Override
  public void createDirectory(String path) {
    Path target = Paths.get(path);
    try {
      Files.createDirectories(target);
    } catch (IOException e) {
      throw new BadRequestException(LogModel.builder()
          .javaExceptionName(e.getClass().getSimpleName())
          .javaExceptionMessage(e.getMessage())
          .build());
    }
  }

  @Override
  public boolean fileExistControl(String path) {
    File f = new File(path);
    return f.isFile();
  }

  @Override
  public void copyFileToTarget(String sourcePath, String targetPath) {
    try {
      Files.copy(Paths.get(sourcePath), Paths.get(targetPath));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public String[] getFileList(String path) {
    String[] fileNames;
    File f = new File(path);
    fileNames = f.list();
    return fileNames;
  }

  @Override
  public String createFileFromEncodedStringData(String encodedData, String path, String fileType) throws IOException {
    if (!dirExistControl(path)) {
      createDirectory(path);
    }
    String fileName = UUID.randomUUID().toString().replace("-", "") + "." + fileType;
    File target = new File(path + "/" + fileName);
    try (OutputStream outputStream = new FileOutputStream(target)) {
      byte[] base64encoded = Base64.getDecoder().decode(encodedData);
      outputStream.write(base64encoded);
      return fileName;
    }
  }


}
