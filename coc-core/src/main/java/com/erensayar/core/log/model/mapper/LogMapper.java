package com.erensayar.core.log.model.mapper;

import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.log.model.entity.LogData;
import com.erensayar.core.log.model.entity.LogEntity;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class LogMapper {

  public LogEntity modelToEntity(LogModel logModel) {
    return LogEntity.builder()
        .traceId(logModel.getTraceId())
        .classNameOfLogPrinted(logModel.getClassNameOfLogPrinted())
        .methodNameOfLogPrinted(logModel.getMethodNameOfLogPrinted())
        .timeStamp(logModel.getTimeStamp())
        .errorCode(logModel.getApiError().getErrorCode())
        .description(logModel.getApiError().getDescription())
        // Nullable
        .userId(logModel.getUserId() == null ? null : logModel.getUserId())
        .logData(logDataBuilder(logModel.getData()))
        .logType(logModel.getLogType() == null ? null : logModel.getLogType())
        .javaExceptionName(logModel.getJavaExceptionName() == null ? null : logModel.getJavaExceptionName())
        .javaExceptionMessage(logModel.getJavaExceptionMessage() == null ? null : logModel.getJavaExceptionMessage())
        .build();
  }

  private List<LogData> logDataBuilder(Map<String, String> data) {
    if (data == null)
      return Collections.emptyList();

    List<LogData> logDataList = new ArrayList<>();
    data.forEach((k, v) ->
        logDataList.add(
            LogData.builder()
                .dataKey(k)
                .dataValue(v)
                .build()
        ));
    return logDataList;
  }

}
