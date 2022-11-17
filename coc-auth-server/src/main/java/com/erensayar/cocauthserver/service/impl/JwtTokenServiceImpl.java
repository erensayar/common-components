package com.erensayar.cocauthserver.service.impl;

import com.erensayar.cocauthserver.component.AuthConstants;
import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.service.JwtTokenService;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class JwtTokenServiceImpl implements JwtTokenService {

    private final AuthConstants authConstants;

    @Override
    public String generateJwtToken(Authentication authentication) {
        User userPrincipal = (User) authentication.getPrincipal();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + authConstants.getToken().getExpireTime()))
                .signWith(SignatureAlgorithm.HS512, authConstants.getToken().getSecretKey())
                .compact();
    }

    @Override
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(authConstants.getToken().getSecretKey()).parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    @Override
    public boolean isExpired(String token) {
        Claims claims = getClaims(token);
        return claims.getExpiration().after(new Date(System.currentTimeMillis()));
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parser()
                .setSigningKey(authConstants.getToken().getSecretKey())
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(authConstants.getToken().getSecretKey()).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            log.error("Invalid JWT signature: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            log.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            log.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            log.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            log.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }


}
