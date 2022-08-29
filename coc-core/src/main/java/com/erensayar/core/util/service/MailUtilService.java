package com.erensayar.core.util.service;

import com.erensayar.core.util.model.dto.Mail;
import com.erensayar.core.util.model.dto.MailChangeDto;
import org.springframework.web.multipart.MultipartFile;

public interface MailUtilService {

  void sendMail(Mail mail);

  void sendMailWithAttachment(Mail mail, MultipartFile attachment);

  void sendMailAsTemplate(Mail mail, Integer mailTemplateNo);

  void sendMailAsTemplateWithAttachment(Mail mail, Integer mailTemplateNo, MultipartFile attachment);

  void sendMailForConfirmMailChangeRequest(String newMail);

  MailChangeDto confirmMailChangeRequest(String confirmCode);

}