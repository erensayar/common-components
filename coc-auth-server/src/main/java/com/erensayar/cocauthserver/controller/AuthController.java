package com.erensayar.cocauthserver.controller;

import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.model.request.LoginRequest;
import com.erensayar.cocauthserver.model.request.SignupRequest;
import com.erensayar.cocauthserver.model.response.LoginResponse;
import com.erensayar.cocauthserver.model.response.SignupResponse;
import com.erensayar.cocauthserver.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/api/auth")
@RestController
public class AuthController {

    private final AuthenticationService authenticationService;
    private final ModelMapper modelMapper;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.signIn(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        User user = authenticationService.signUp(signUpRequest);
        return ResponseEntity.ok(modelMapper.map(user, SignupResponse.class));
    }
}
