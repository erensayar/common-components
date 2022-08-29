package com.erensayar.core.error.exception;

import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.error.model.ApiError;

public class OkWithMessage extends BaseException {

  private static final Integer ERROR_CODE = BaseExceptionConstant.OK_CODE;
  private static final String ERROR_MESSAGE = BaseExceptionConstant.OK_MESSAGE;

  public OkWithMessage() {
    super(ERROR_CODE, ERROR_MESSAGE, LogModel.builder()
        .apiError(ApiError.of((ERROR_CODE), ERROR_MESSAGE))
        .build());
  }

  public OkWithMessage(final LogModel logModel) {
    super(logModel.getApiError().getErrorCode(),
          logModel.getApiError().getDescription(),
          logModel);
  }

}