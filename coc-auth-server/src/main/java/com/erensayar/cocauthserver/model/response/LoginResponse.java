package com.erensayar.cocauthserver.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String type;
    private String token;
    private UUID userId;
    private String username;
    private String userEmail;
    private Collection<? extends GrantedAuthority> roles = new ArrayList<>();

    public LoginResponse(String token, UUID userId, String username, String userEmail, Collection<? extends GrantedAuthority> roles) {
        this.type = "Bearer";
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.userEmail = userEmail;
        this.roles = roles;
    }

}
