package com.erensayar.cocauthserver.service.util;

import com.erensayar.cocauthserver.model.entity.User;
import java.util.UUID;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

@NoArgsConstructor
public class Principal {

  public static User getUser() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    if (principal instanceof User) {
      return ((User) principal);
    } else {
      return new User();
    }
  }

  public static boolean isLoggedIn() {
    Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    return principal instanceof User;
  }

  public static UUID getUserId() {
    return getUser().getId();
  }

  public static String getEmail() {
    return getUser().getEmail();
  }

  public static String getUsername() {
    return getUser().getUsername();
  }

}
