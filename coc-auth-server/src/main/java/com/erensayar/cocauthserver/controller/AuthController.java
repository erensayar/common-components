package com.erensayar.cocauthserver.controller;

import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.model.request.LoginRequest;
import com.erensayar.cocauthserver.model.request.SignupRequest;
import com.erensayar.cocauthserver.model.response.LoginResponse;
import com.erensayar.cocauthserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<User> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(authenticationService.signUp(signUpRequest));
    }
}
