package com.erensayar.core.error.exception;

import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.error.model.ApiError;

public class UnauthorizedException extends BaseException {

  private static final Integer ERROR_CODE = BaseExceptionConstant.UNAUTHORIZED_ERROR_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstant.UNAUTHORIZED_ERROR_MESSAGE;

  public UnauthorizedException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of((ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public UnauthorizedException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode(),
          logModel.getApiError().getDescription(),
          logModel);
  }

}
