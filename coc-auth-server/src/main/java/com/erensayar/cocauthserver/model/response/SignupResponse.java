package com.erensayar.cocauthserver.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class SignupResponse {
    private UUID userId;
    private String username;
    private String email;
    private String mailVerification;
}
