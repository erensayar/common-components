package com.erensayar.cocauthserver.model.dto;

import com.erensayar.cocauthserver.model.enums.Role;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserInfo {

  private UUID id;
  private String username;
  private String email;
  private Set<Role> roles = new HashSet<>();
  private Date tokenExpireTime;

}
