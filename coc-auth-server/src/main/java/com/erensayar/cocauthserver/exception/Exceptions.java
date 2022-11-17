package com.erensayar.cocauthserver.exception;

import com.erensayar.core.error.model.ApiError;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Exceptions {

    // USER
    public static final ApiError USER_NO_CONTENT_BY_ID =
            ApiError.of(7001, "User can not find with this id.");

    public static final ApiError USER_NO_CONTENT_BY_EMAIL =
            ApiError.of(7002, "User can not find with this email.");

    public static final ApiError USER_NO_CONTENT_BY_USERNAME =
            ApiError.of(7003, "User can not find with this username.");

    public static final ApiError CONFLICT_USERNAME =
            ApiError.of(7004, "Username is already taken.");

    public static final ApiError CONFLICT_EMAIL =
            ApiError.of(7005, "Email is already in use.");

    public static final ApiError EMAIL_NOT_VERIFIED =
            ApiError.of(7006, "Email is not verified.");

    public static final ApiError USER_UNAUTHORIZED_ACCESS_REQUEST =
            ApiError.of(7007, "You have not permission access to user object.");


}