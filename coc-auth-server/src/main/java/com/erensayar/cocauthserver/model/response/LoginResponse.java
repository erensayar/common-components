package com.erensayar.cocauthserver.model.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class LoginResponse {
  private String type;
  private String token;
  private Long id;
  private String username;
  private String email;

  public LoginResponse(String token, Long id, String username, String email) {
    this.type = "Bearer";
    this.token = token;
    this.id = id;
    this.username = username;
    this.email = email;
  }

}
