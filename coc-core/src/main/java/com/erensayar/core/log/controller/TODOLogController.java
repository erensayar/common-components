package com.erensayar.core.log.controller;

/* TODO : Bu pathi admin erişimine açtım yalnızca, buranın erişimi sınırlı olmalı ancak
    jwt'nin çalışma mantığından dolayı core'u başka proje içinde kullandığımda bu
    endpointlere authorize olamıyorum. Şimdilik bu controller'ı direkt core'u dependency
    olarak eklediğim modülde duplicatre şekilde yazacağım buna çözüm gerek.


@RequiredArgsConstructor
@RequestMapping("/api/admin/logs")
@RestController
public class LogController {

  private final LogService logService;

  @GetMapping
  public ResponseEntity<List<LogEntity>> getLogs() {
    return ResponseEntity.ok(logService.getLogs());
  }

  @GetMapping(path = "getLogByTraceId/{traceId}")
  public ResponseEntity<LogEntity> getLogByTraceId(@PathVariable String traceId) {
    return ResponseEntity.ok(logService.getLogByTraceId(traceId));
  }

  @GetMapping(path = "getLogsByUserId/{userId}")
  public ResponseEntity<List<LogEntity>> getLogsByUserId(@PathVariable String userId) {
    return ResponseEntity.ok(logService.getLogsByUserId(userId));
  }

  @GetMapping(path = "getLogsByErrorCode/{errorCode}")
  public ResponseEntity<List<LogEntity>> getLogsByErrorCode(@PathVariable Integer errorCode) {
    return ResponseEntity.ok(logService.getLogsByErrorCode(errorCode));
  }

  @GetMapping(path = "getLogsByLogType/{logType}")
  public ResponseEntity<List<LogEntity>> getLogsByLogType(@PathVariable Integer logType) {
    return ResponseEntity.ok(logService.getLogsByLogType(LogType.getValById(logType)));
  }

  @GetMapping(path = "deleteAllByLogType/{logType}")
  public ResponseEntity<String> deleteAllByLogType(@PathVariable Integer logType) {
    logService.deleteAllByLogType(LogType.getValById(logType));
    return ResponseEntity.ok(null);
  }

  @GetMapping(path = "deleteAllByUserId/{userId}")
  public ResponseEntity<String> deleteAllByUserId(@PathVariable String userId) {
    logService.deleteAllByUserId(userId);
    return ResponseEntity.ok(null);
  }

}

*/