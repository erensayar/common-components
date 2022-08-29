package com.erensayar.core.error.exception;

import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.error.model.ApiError;

public class ConflictException extends BaseException {

  private static final Integer ERROR_CODE = BaseExceptionConstant.CONFLICT_ERROR_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstant.CONFLICT_ERROR_MESSAGE;

  public ConflictException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of((ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public ConflictException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode(),
          logModel.getApiError().getDescription(),
          logModel);
  }

}
