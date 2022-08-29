package com.erensayar.core.error.exception;

import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.error.model.ApiError;

public class NoContentException extends BaseException {

  private static final Integer ERROR_CODE = BaseExceptionConstant.NO_CONTENT_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstant.NO_CONTENT_MESSAGE;

  public NoContentException() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of((ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public NoContentException(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode(),
          logModel.getApiError().getDescription(),
          logModel);
  }
}
