package com.erensayar.cocauthserver.model.request;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {

  @NotNull
  private String username;

  @NotNull
  private String password;

}
