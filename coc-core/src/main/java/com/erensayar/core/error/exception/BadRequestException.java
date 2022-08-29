package com.erensayar.core.error.exception;

import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.error.model.ApiError;

public class BadRequestException extends BaseException {

  private static final Integer ERROR_CODE = BaseExceptionConstant.BAD_REQUEST_ERROR_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstant.BAD_REQUEST_ERROR_MESSAGE;

  public BadRequestException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(ERROR_CODE, ERROR_MESSAGE))
        .build());
  }

  public BadRequestException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode(),
          logModel.getApiError().getDescription(),
          logModel);
  }

}









/*
public class BadRequestException extends BaseException {

  private static final String ERROR_CODE = BaseExceptionConstants.BAD_REQUEST_ERROR_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstants.BAD_REQUEST_ERROR_MESSAGE;

 public BadRequestException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public BadRequestException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode().toString(), logModel.getApiError().getDescription(), logModel);
  }

    public BadRequestException(final ApiError apiError) {
    super(apiError.getErrorCode().toString(), apiError.getDescription(), LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), apiError.getDescription()))
        .build());
  }

  public BadRequestException(final String errorMessage) {
    super(ERROR_CODE, errorMessage, LogModel.builder()
        .apiError(ApiError.of(Integer.valueOf(ERROR_CODE), errorMessage))
        .build());
  }
}
 */