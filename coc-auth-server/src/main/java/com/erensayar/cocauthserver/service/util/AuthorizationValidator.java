package com.erensayar.cocauthserver.service.util;

import com.erensayar.cocauthserver.exception.Exceptions;
import com.erensayar.core.error.exception.ForbiddenException;
import com.erensayar.core.error.model.ApiError;
import com.erensayar.core.log.model.dto.LogModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public abstract class AuthorizationValidator {

    private void baseValidator(UUID userId, String objectName, String objectId, ApiError apiError) {
        UUID loggedUser = Principal.getUserId();
        if (!loggedUser.equals(userId)) {
            throw new ForbiddenException(LogModel.builder()
                    .userId(loggedUser.toString())
                    .apiError(apiError)
                    .data(Map.of(
                            "User id client wants to access: ", userId.toString(),
                            "Object name client wants to access: ", objectName,
                            "Object id client wants to access: ", objectId))
                    .build());
        }
    }


    public void userAuthorizationValidator(UUID userId) {
        baseValidator(userId, "User", userId.toString(), Exceptions.USER_UNAUTHORIZED_ACCESS_REQUEST);
    }

}
