package com.erensayar.cocauthserver.service;

import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.model.request.LoginRequest;
import com.erensayar.cocauthserver.model.request.SignupRequest;
import com.erensayar.cocauthserver.model.response.LoginResponse;

public interface AuthenticationService {

    User signUp(SignupRequest signUpRequest);

    LoginResponse signIn(LoginRequest loginRequest);

    void confirmMail(String confirmCode);

}
