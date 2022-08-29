package com.erensayar.core.log.service.impl;

import static com.erensayar.core.log.model.enums.LogType.getLogTypes;

import com.erensayar.core.error.exception.NoContentException;
import com.erensayar.core.log.model.entity.LogEntity;
import com.erensayar.core.log.model.enums.LogType;
import com.erensayar.core.log.repository.LogEntityRepository;
import com.erensayar.core.log.service.LogService;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@RequiredArgsConstructor
@Service
public class LogServiceImpl implements LogService {

  private final LogEntityRepository logEntityRepository;

  @Value("${core.log.storage-time.info}")
  private int infoLogStorageTime;

  @Value("${core.log.storage-time.warning}")
  private int warningLogStorageTime;

  @Value("${core.log.storage-time.technical-error}")
  private int technicalErrorLogStorageTime;

  @Value("${core.log.storage-time.business-error}")
  private int businessErrorLogStorageTime;

  @Override
  public List<LogEntity> getLogs() {
    return logEntityRepository.findAll();
  }

  @Override
  public LogEntity getLogByTraceId(String traceId) {
    return logEntityRepository.findByTraceId(traceId).orElseThrow(NoContentException::new);
  }

  @Override
  public List<LogEntity> getLogsByUserId(String userId) {
    return logEntityRepository.findAllByUserId(userId);
  }

  @Override
  public List<LogEntity> getLogsByErrorCode(Integer errorCode) {
    return logEntityRepository.findAllByErrorCode(errorCode);
  }

  @Override
  public List<LogEntity> getLogsByLogType(LogType logType) {
    return logEntityRepository.findAllByLogType(logType);
  }

  @Override
  public void deleteAllByLogType(LogType logType) {
    logEntityRepository.deleteAllByLogType(logType);
  }

  @Override
  public void deleteAllByLogTypeAndReachedTheTimeLimit(LogType logType) {
    LocalDateTime referenceTimeForDeleteBefore = null;
    LocalDateTime now = LocalDateTime.now();
    switch (logType) {
      case INFO -> {
        if (infoLogStorageTime==-1) return;
        referenceTimeForDeleteBefore = now.minusDays(infoLogStorageTime);
      }
      case WARNING -> {
        if (warningLogStorageTime==-1) return;
        referenceTimeForDeleteBefore = now.minusDays(warningLogStorageTime);
      }
      case TECHNICAL_ERROR -> {
        if (technicalErrorLogStorageTime==-1) return;
        referenceTimeForDeleteBefore = now.minusDays(technicalErrorLogStorageTime);
      }
      case BUSINESS_ERROR -> {
        if (businessErrorLogStorageTime==-1) return;
        referenceTimeForDeleteBefore = now.minusDays(businessErrorLogStorageTime);
      }
    }
    logEntityRepository.deleteAllByLogTypeAndTimeStampBefore(logType, referenceTimeForDeleteBefore);
  }

  @Override
  public void deleteAllByUserId(String userId) {
    logEntityRepository.deleteAllByUserId(userId);
  }

  @Scheduled(cron = "0 0 3 * * *") // 03.00 A.M.
  public void cleanLogsByDefaultExpireTime() {
    LogType[] logTypes = getLogTypes();
    for (LogType l : logTypes) {
      deleteAllByLogTypeAndReachedTheTimeLimit(l);
    }
  }

}