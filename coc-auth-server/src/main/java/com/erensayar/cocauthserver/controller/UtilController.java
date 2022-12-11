package com.erensayar.cocauthserver.controller;

import com.erensayar.cocauthserver.model.dto.UserInfo;
import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.service.JwtTokenService;
import com.erensayar.cocauthserver.service.util.Principal;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/utils")
@RestController
public class UtilController {

  private final JwtTokenService jwtTokenService;
  private final ModelMapper modelMapper;

  @GetMapping("")
  @PreAuthorize(value = "hasAnyAuthority('USER')")
  public UserInfo getUserInfo(@RequestHeader("Authorization") String token) {
    User user = Principal.getUser();
    Claims claims = jwtTokenService.getClaims(token.substring(7));
    UserInfo userInfo = modelMapper.map(user, UserInfo.class);
    userInfo.setTokenExpireTime(claims.getExpiration());
    return userInfo;
  }


}
