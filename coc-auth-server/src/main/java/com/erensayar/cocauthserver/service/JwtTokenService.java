package com.erensayar.cocauthserver.service;

import io.jsonwebtoken.Claims;
import org.springframework.security.core.Authentication;

public interface JwtTokenService {

    String generateJwtToken(Authentication authentication);

    String getUserNameFromJwtToken(String token);

    boolean isExpired(String token);

    Claims getClaims(String token);

    boolean validateJwtToken(String authToken);

}
