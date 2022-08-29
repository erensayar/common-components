package com.erensayar.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CoreApplication {

  public static void main(String[] args) {
    SpringApplication.run(CoreApplication.class, args);
  }


}

/*
@Autowired
  private LogEntityRepository logEntityRepository;

  @Override
  public void run(String... args) throws Exception {

    LogData logData1 = LogData.builder()
        .key("dasdasdasd")
        .value("asdasdasd")
        .build();
    //LogData savedLogData1 = logDataRepository.save(logData1);

    LogData logData2 = LogData.builder()
        .key("ssdf")
        .value("464")
        .build();
    //LogData savedLogData2 = logDataRepository.save(logData2);

    LogData logData3 = LogData.builder()
        .key("adas")
        .value("h566ygh6")
        .build();
    //LogData savedLogData3 = logDataRepository.save(logData3);

    List<LogData> logDataList = new ArrayList<>();
    logDataList.add(logData1);
    logDataList.add(logData2);
    logDataList.add(logData3);

    LogEntity logEntity1 = LogEntity.builder()
        .traceId(UUID.randomUUID())
        .classNameOfLogPrinted("aasdasdasdas")
        .methodNameOfLogPrinted("aasdasdasdas")
        .userId("aasdasdasdas")
        .errorCode(3481231)
        .description("aasdasdasdas")
        .javaExceptionName("aasdasdasdas")
        .javaExceptionMessage("aasdasdasdas")
        .timeStamp(LocalDateTime.now())
        .logData(logDataList)
        .build();

    logEntityRepository.save(logEntity1);
  }
 */
