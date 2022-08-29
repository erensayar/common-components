package com.erensayar.core.error.exception;

import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.error.model.ApiError;

public class TooManyRequestException extends BaseException {

  private static final Integer ERROR_CODE = BaseExceptionConstant.TOO_MANY_REQUEST_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstant.TOO_MANY_REQUEST_MESSAGE;

  public TooManyRequestException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of((ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public TooManyRequestException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode(),
          logModel.getApiError().getDescription(),
          logModel);
  }

}
