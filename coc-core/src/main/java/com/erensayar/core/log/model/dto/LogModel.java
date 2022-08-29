package com.erensayar.core.log.model.dto;

import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.model.enums.LogType;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@Builder
public class LogModel {

  // Default Created
  private String traceId;
  private String classNameOfLogPrinted; // class where the thrown error
  private String methodNameOfLogPrinted;// method where the thrown error
  private LocalDateTime timeStamp;

  // Custom Created
  private String userId;                // if we have - take from module (other module take from principal then send core)
  private ApiError apiError;            // problem code, problem description (business)
  private Map<String, String> data;     // entity name , entity id etc... relevant data about log
  private LogType logType;              // LogType. Business_error or technical error
  private String javaExceptionName;     // source exception name
  private String javaExceptionMessage;  // source exception message

  public LogModel(
      String traceId,
      String classNameOfLogPrinted,
      String methodNameOfLogPrinted,
      LocalDateTime timeStamp,
      String userId,
      ApiError apiError,
      Map<String, String> data,
      LogType logType,
      String javaExceptionName,
      String javaExceptionMessage) {

    StackTraceElement[] ste = Thread.currentThread().getStackTrace();
    this.traceId = UUID.randomUUID().toString().replace("-", "");
    this.classNameOfLogPrinted = ste[3].getClassName();
    this.methodNameOfLogPrinted = ste[3].getMethodName();
    this.timeStamp = LocalDateTime.now();
    this.userId = userId;
    this.apiError = apiError;
    this.data = data;
    this.logType = logType;
    this.javaExceptionName = javaExceptionName;
    this.javaExceptionMessage = javaExceptionMessage;
  }

  public void createErrorLog() {
    log.error(toString());
  }

  public void createInfoLog() {
    log.info(toString());
  }

  public void createDebugLog() {
    log.debug(toString());
  }

  public void createWarnLog() {
    log.warn(toString());
  }

  // Builder customization - For just example
  // <=============================================================================================>
  // public static LogModelBuilder builder() {
  //   return new CustomLogModelBuilder();
  // }
  //
  // private static class CustomLogModelBuilder extends LogModelBuilder {
  //
  //   @Override
  //   public LogModel build() {
  //     StackTraceElement[] ste = Thread.currentThread().getStackTrace();
  //     super.traceId(UUID.randomUUID().toString().replace("-", ""));
  //     super.methodNameOfLogPrinted(ste[2].getMethodName()); // Burasi 2 iken constructor 4 olmali
  //     super.classNameOfLogPrinted(ste[2].getClassName());   // Burasi 2 iken constructor 4 olmali
  //     super.timeStamp(LocalDateTime.now());
  //     return super.build();
  //   }
  //
  // }
  // <=============================================================================================>

  @Override
  public String toString() {
    return '\n' + "LogModel {" + '\n' +
        "  traceId='" + traceId + '\n' +
        "  classNameOfLogPrinted='" + classNameOfLogPrinted + '\n' +
        "  methodNameOfLogPrinted='" + methodNameOfLogPrinted + '\n' +
        "  timeStamp=" + timeStamp + '\n' +
        "  userId='" + userId + '\n' +
        "  apiError=" + apiError + '\n' +
        "  data=" + data + '\n' +
        "  javaExceptionName='" + javaExceptionName + '\n' +
        "  javaExceptionMessage='" + javaExceptionMessage + '\n' +
        "  logType='" + logType + '\n' +
        '}';
  }

}
