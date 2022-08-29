package com.erensayar.core.log.service;

import com.erensayar.core.log.model.entity.LogEntity;
import com.erensayar.core.log.model.enums.LogType;
import java.util.List;

public interface LogService {

  List<LogEntity> getLogs();

  LogEntity getLogByTraceId(String traceId);

  List<LogEntity> getLogsByUserId(String userId);

  List<LogEntity> getLogsByErrorCode(Integer errorCode);

  List<LogEntity> getLogsByLogType(LogType logType);

  void deleteAllByLogType(LogType logType);

  void deleteAllByLogTypeAndReachedTheTimeLimit(LogType logType);

  void deleteAllByUserId(String userId);

}
