package com.erensayar.core.error.exception;

import com.erensayar.core.error.model.ApiError;
import lombok.experimental.UtilityClass;

@UtilityClass
public class BaseExceptionConstant {

  // BASE EXCEPTION CONSTANTS
  static final Integer BASE_ERROR_CODE                       = 1000;
  static final String BASE_ERROR_MESSAGE                     = "Error!";
  static final Integer BAD_REQUEST_ERROR_CODE                = 1001;
  static final String BAD_REQUEST_ERROR_MESSAGE              = "Bad Request";
  static final Integer NOT_FOUND_ERROR_CODE                  =  1002;
  static final String NOT_FOUND_ERROR_MESSAGE                = "Not Found";
  static final Integer CONFLICT_ERROR_CODE                   = 1003;
  static final String CONFLICT_ERROR_MESSAGE                 = "Content Conflict";
  static final Integer UNSUPPORTED_MEDIA_TYPE_ERROR_CODE     = 1004;
  static final String UNSUPPORTED_MEDIA_TYPE_ERROR_MESSAGE   = "Unsupported Media Type";
  static final Integer INTERNAL_SERVER_ERROR_CODE            = 1005;
  static final String INTERNAL_SERVER_ERROR_MESSAGE          = "Internal Server Error";
  static final Integer UNAUTHORIZED_ERROR_CODE               = 1006;
  static final String UNAUTHORIZED_ERROR_MESSAGE             = "Unauthorized";
  static final Integer FORBIDDEN_ERROR_CODE                  = 1007;
  static final String FORBIDDEN_ERROR_MESSAGE                = "Forbidden";
  static final Integer NO_CONTENT_CODE                       = 1008;
  static final String NO_CONTENT_MESSAGE                     = "No Content";
  static final Integer OK_CODE                               = 1009;
  static final String OK_MESSAGE                             = "OK";
  static final Integer TOO_MANY_REQUEST_CODE                 = 1010;
  static final String TOO_MANY_REQUEST_MESSAGE               = "Too Many Request! Handled in interceptor... Client has been reached maximum request limit per minute";

  // BASE EXCEPTIONS
  public static final ApiError BASE_ERROR =
      ApiError.of(BASE_ERROR_CODE, BASE_ERROR_MESSAGE);

  public static final ApiError  BAD_REQUEST_ERROR=
      ApiError.of(BAD_REQUEST_ERROR_CODE, BAD_REQUEST_ERROR_MESSAGE);

  public static final ApiError NOT_FOUND_ERROR =
      ApiError.of(NOT_FOUND_ERROR_CODE, NOT_FOUND_ERROR_MESSAGE);

  public static final ApiError CONFLICT_ERROR =
      ApiError.of(CONFLICT_ERROR_CODE, CONFLICT_ERROR_MESSAGE);

  public static final ApiError UNSUPPORTED_MEDIA_TYPE_ERROR =
      ApiError.of(UNSUPPORTED_MEDIA_TYPE_ERROR_CODE, UNSUPPORTED_MEDIA_TYPE_ERROR_MESSAGE);

  public static final ApiError INTERNAL_SERVER_ERROR =
      ApiError.of(INTERNAL_SERVER_ERROR_CODE, INTERNAL_SERVER_ERROR_MESSAGE);

  public static final ApiError UNAUTHORIZED_ERROR =
      ApiError.of(UNAUTHORIZED_ERROR_CODE, UNAUTHORIZED_ERROR_MESSAGE);

  public static final ApiError FORBIDDEN_ERROR =
      ApiError.of(FORBIDDEN_ERROR_CODE, FORBIDDEN_ERROR_MESSAGE);

  public static final ApiError NO_CONTENT =
      ApiError.of(NO_CONTENT_CODE, NO_CONTENT_MESSAGE);

  public static final ApiError OK =
      ApiError.of(OK_CODE, OK_MESSAGE);

  public static final ApiError TOO_MANY_REQUEST =
      ApiError.of(TOO_MANY_REQUEST_CODE, TOO_MANY_REQUEST_MESSAGE);

  // GENERAL EXCEPTIONS
  public static final ApiError VALIDATION_EXCEPTION =
      ApiError.of(2001, "Not Valid");

  public static final ApiError CACHE_INITIALIZE_PROBLEM =
      ApiError.of(2002, "Problem initializing cache");

  public static final ApiError CACHE_PUT_PROBLEM =
      ApiError.of(2003, "Problem putting object in the cache");

  public static final ApiError NO_PERMISSION_FOR_CHANGE_THE_OBJ_PROP =
      ApiError.of(2004, "No permission for change the object properties.");

  public static final ApiError MAIL_RECEIVER_NULL =
      ApiError.of(2005, "Mail receiver can not be null.");

  public static final ApiError MAIL_CONFIRM_CODE_IS_WRONG =
      ApiError.of(2006, "Mail confirm code is wrong.");

}
