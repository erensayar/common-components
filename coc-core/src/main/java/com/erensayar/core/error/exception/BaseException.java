package com.erensayar.core.error.exception;

import com.erensayar.core.log.model.dto.LogModel;
import lombok.Getter;

@Getter
public class BaseException extends RuntimeException {

  private final Integer errorCode;
  private final String errorMessage;
  private final LogModel logModel;

  public BaseException() {
    this.errorCode = BaseExceptionConstant.BASE_ERROR_CODE;
    this.errorMessage = BaseExceptionConstant.BASE_ERROR_MESSAGE;
    this.logModel = null;
  }

  public BaseException(final Integer errCode, final String errorMessage, final LogModel logModel) {
    this.errorCode = errCode;
    this.errorMessage = errorMessage;
    this.logModel = logModel;
  }

  public BaseException(final Integer errCode, final String errorMessage) {
    this.errorCode = errCode;
    this.errorMessage = errorMessage;
    this.logModel = null;
  }

  public BaseException(final String errorMessage, final LogModel logModel) {
    this.errorCode = BaseExceptionConstant.BASE_ERROR_CODE;
    this.errorMessage = errorMessage;
    this.logModel = logModel;
  }

  public BaseException(final String errorMessage) {
    this.errorCode = BaseExceptionConstant.BASE_ERROR_CODE;
    this.errorMessage = errorMessage;
    this.logModel = null;
  }

}
