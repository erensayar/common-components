package com.erensayar.core.error.model;

import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ErrorResponse {
  private Integer errorCode;
  private String errorMessage;
  private LocalDateTime timeStamp;
}
