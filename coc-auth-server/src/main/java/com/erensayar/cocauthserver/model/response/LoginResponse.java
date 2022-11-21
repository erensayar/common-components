package com.erensayar.cocauthserver.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
    private String type;
    private String token;
    private UUID userId;
    private String username;
    private String email;
    //private Collection<? extends GrantedAuthority> roles = new ArrayList<>();

    public LoginResponse(String token, UUID userId, String username, String email) {
        this.type = "Bearer";
        this.token = token;
        this.userId = userId;
        this.username = username;
        this.email = email;
    }

}
