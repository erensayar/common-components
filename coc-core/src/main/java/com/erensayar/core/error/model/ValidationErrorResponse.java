package com.erensayar.core.error.model;

import java.time.LocalDateTime;
import java.util.Map;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidationErrorResponse {

  private Integer errorCode;

  private String errorType;

  private Map<String, String> errorMessage;

  @Builder.Default
  private LocalDateTime timeStamp = LocalDateTime.now();

}
