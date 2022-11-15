package com.erensayar.cocauthserver.model.request;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.erensayar.cocauthserver.model.enums.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

  @NotNull(message = "{api.constraint.message.username.not-null}")
  @Size(message = "{api.constraint.message.username.size}", min = 5, max = 50)
  @Column(name = "username", length = 50)
  private String username;

  @Email
  @NotNull(message = "{api.constraint.message.email.not-null}")
  @Size(message = "{api.constraint.message.email.size}", min = 8, max = 50)
  @Pattern(message = "{api.constraint.message.email.pattern}", regexp = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$")
  @Column(name = "email", nullable = false, unique = true, length = 50)
  private String email;

  @NotNull(message = "{api.constraint.message.password.not-null}")
  @Size(message = "{api.constraint.message.password.size}", min = 8, max = 50)
  @Pattern(message = "{api.constraint.message.password.pattern}", regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).*$")
  private String password;

  private Role role;

}