package com.erensayar.core.util.model.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MailChangeDto {
  private String confirmCode;
  private String newMail;
}
