package com.erensayar.core.util.service.implementation;

import com.erensayar.core.error.exception.BadRequestException;
import com.erensayar.core.error.exception.BaseExceptionConstant;
import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.util.model.dto.Mail;
import com.erensayar.core.util.model.dto.MailChangeDto;
import com.erensayar.core.util.model.enums.MailTemplateName;
import com.erensayar.core.util.service.CacheService;
import com.erensayar.core.util.service.MailUtilService;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.UUID;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Slf4j
@RequiredArgsConstructor
@Service
public class MailUtilServiceImpl implements MailUtilService {

  private final JavaMailSender javaMailSender;
  private final SpringTemplateEngine templateEngine;
  private final CacheService<MailChangeDto> cacheService;

  @Override
  public void sendMail(Mail mail) {
    SimpleMailMessage mailMessage = new SimpleMailMessage();

    if (mail.getTo() == null) {
      throw new BadRequestException(LogModel.builder()
          .apiError(BaseExceptionConstant.MAIL_RECEIVER_NULL)
          .build());
    }
    mailMessage.setTo(mail.getTo());
    mailMessage.setCc(mail.getCc());
    mailMessage.setBcc(mail.getBcc());
    mailMessage.setSubject(mail.getSubject());
    mailMessage.setText(mail.getBody());
    javaMailSender.send(mailMessage);
  }


  @Override
  public void sendMailWithAttachment(Mail mail, MultipartFile attachment) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = createMimeMessageHelper(mail, msg);

      // Attachment
      byte[] byteArr = attachment.getBytes();
      InputStream inputStream = new ByteArrayInputStream(byteArr);
      ByteArrayDataSource attachmentAsByteArrayDataSource = new ByteArrayDataSource(inputStream, "application/octet-stream");
      mimeMessageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), attachmentAsByteArrayDataSource);

      javaMailSender.send(msg);
    } catch (MessagingException | IOException e) {
      log.error(e.getMessage());
    }
  }


  @Override
  public void sendMailAsTemplate(Mail mail, Integer mailTemplateNo) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = createMimeMessageHelper(mail, msg);

      // Template
      Context context = new Context();
      context.setVariables(mail.getProps());
      String htmlFileName = MailTemplateName.values()[mailTemplateNo].toString();
      String html = templateEngine.process(htmlFileName, context);
      mimeMessageHelper.setText(html, true); // Set mail body as template

      javaMailSender.send(msg);
    } catch (MessagingException e) {
      log.error(e.getMessage());
    }
  }


  @Override
  public void sendMailAsTemplateWithAttachment(Mail mail, Integer mailTemplateNo, MultipartFile attachment) {
    try {
      MimeMessage msg = javaMailSender.createMimeMessage();
      MimeMessageHelper mimeMessageHelper = createMimeMessageHelper(mail, msg);

      // Template
      Context context = new Context();
      context.setVariables(mail.getProps());
      String htmlFileName = MailTemplateName.values()[mailTemplateNo].toString();
      String html = templateEngine.process(htmlFileName, context);
      mimeMessageHelper.setText(html, true); // Set mail body as template

      // Attachment
      byte[] byteArr = attachment.getBytes();
      InputStream inputStream = new ByteArrayInputStream(byteArr);
      ByteArrayDataSource attachmentAsByteArrayDataSource = new ByteArrayDataSource(inputStream, "application/octet-stream");
      mimeMessageHelper.addAttachment(Objects.requireNonNull(attachment.getOriginalFilename()), attachmentAsByteArrayDataSource);

      javaMailSender.send(msg);
    } catch (MessagingException | IOException e) {
      log.error(e.getMessage());
    }
  }

  /**
   * This method does receive new mail from client and insert to cache also generate confirm code.
   *
   * @param newMail New Mail
   */
  @Override
  public void sendMailForConfirmMailChangeRequest(String newMail) {
    String confirmCode = UUID.randomUUID().toString().replace("-", "");
    cacheService.put(confirmCode, MailChangeDto.builder()
        .confirmCode(confirmCode)
        .newMail(newMail)
        .build());
  }

  /**
   * Client click the generated confirm link then run here and get the object by key from cache. If confirm code is
   * present in the cache mail change process can be start.
   *
   * @param confirmCode client send the received confirm code. This code generate in mail change request method if they
   *                    equal it's done.
   * @return New Mail
   */
  @Override
  public MailChangeDto confirmMailChangeRequest(String confirmCode) {
    MailChangeDto mailChangeDto = cacheService.findByKey(confirmCode);
    boolean codesAreEqual = mailChangeDto.getConfirmCode().equals(confirmCode);
    if (!codesAreEqual) {
      throw new IllegalArgumentException();
    }
    return mailChangeDto;
  }

  // <=================================================================================================================>

  private MimeMessageHelper createMimeMessageHelper(Mail mail, MimeMessage mimeMessage) throws MessagingException {
    MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
        mimeMessage,
        MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
        StandardCharsets.UTF_8.name());

    // Create Mail
    mimeMessageHelper.setTo(mail.getTo());
    mimeMessageHelper.setCc(mail.getCc());
    mimeMessageHelper.setBcc(mail.getBcc());
    mimeMessageHelper.setSubject(mail.getSubject());
    mimeMessageHelper.setText(mail.getBody());
    return mimeMessageHelper;
  }


}