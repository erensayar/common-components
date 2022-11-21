package com.erensayar.cocauthserver.service.impl;

import com.erensayar.cocauthserver.component.MailConstants;
import com.erensayar.cocauthserver.exception.Exceptions;
import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.model.request.LoginRequest;
import com.erensayar.cocauthserver.model.request.SignupRequest;
import com.erensayar.cocauthserver.model.response.LoginResponse;
import com.erensayar.cocauthserver.repository.UserRepository;
import com.erensayar.cocauthserver.service.AuthenticationService;
import com.erensayar.cocauthserver.service.JwtTokenService;
import com.erensayar.cocauthserver.service.UserService;
import com.erensayar.core.error.exception.BadRequestException;
import com.erensayar.core.error.exception.BaseExceptionConstant;
import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.log.model.enums.LogType;
import com.erensayar.core.util.model.dto.Mail;
import com.erensayar.core.util.model.dto.MailChangeDto;
import com.erensayar.core.util.service.MailUtilService;
import com.erensayar.core.util.service.UtilService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AuthenticationServiceImpl implements AuthenticationService {


    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final UserRepository userRepository;
    private final JwtTokenService jwtTokenService;
    private final UtilService utilService;
    private final MailUtilService mailUtilService;
    private final MailConstants mailConstants;


    @Override
    public User signUp(SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            throw new BadRequestException(LogModel.builder()
                    .apiError(Exceptions.CONFLICT_USERNAME)
                    .logType(LogType.WARNING)
                    .data(Map.of("Username", signUpRequest.getUsername()))
                    .build());
        }
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException(LogModel.builder()
                    .apiError(Exceptions.CONFLICT_EMAIL)
                    .logType(LogType.WARNING)
                    .data(Map.of("Email", signUpRequest.getEmail()))
                    .build());
        }

        // If, Used For Easy Development
        if (!utilService.activeProfileCheck("default") && !utilService.activeProfileCheck("local") ) {
            mailUtilService.sendConfirmMail(Mail.builder()
                    .to(signUpRequest.getEmail())
                    .subject(mailConstants.getMail().getConfirm().getSubject())
                    .body(mailConstants.getMail().getConfirm().getBody() + ": " + mailConstants.getMail().getConfirm().getBaseConfirmUrl())
                    .build());
        }

        return userService.save(signUpRequest);
    }


    @Override
    public LoginResponse signIn(LoginRequest signInRequest) {
        checkMailConfirm(signInRequest);
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequest.getUsername(),
                        signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwtToken = jwtTokenService.generateJwtToken(authentication);

        User user = (User) authentication.getPrincipal();

        return new LoginResponse(
                jwtToken,
                user.getId(),
                user.getUsername(),
                user.getEmail());
    }


    @Override
    public void confirmMail(String confirmCode) {
        MailChangeDto mailChangeDto = null;
        try {
            mailChangeDto = mailUtilService.confirmTheNewMail(confirmCode);
        } catch (IllegalArgumentException e) {
            throw new BadRequestException(LogModel.builder()
                    .apiError(BaseExceptionConstant.MAIL_CONFIRM_CODE_IS_WRONG)
                    .data(Map.of("ConfirmCode", confirmCode))
                    .build());
        }
        User user = userService.findByEmail(mailChangeDto.getNewMail());
        user.setMailVerification(true);
        userRepository.save(user);
    }


    // PRIVATE
    // <=================================================================================================================>


    private void checkMailConfirm(LoginRequest signInRequest) {
        Optional<User> optAcc = userRepository.findByUsername(signInRequest.getUsername());
        if (optAcc.isEmpty()) {
            throw new BadRequestException(LogModel.builder()
                    .apiError(Exceptions.USER_NO_CONTENT_BY_USERNAME)
                    .logType(LogType.WARNING)
                    .data(Map.of("username", signInRequest.getUsername()))
                    .build());
        }
        if (!optAcc.get().getMailVerification()) {
            throw new BadRequestException(LogModel.builder()
                    .apiError(Exceptions.EMAIL_NOT_VERIFIED)
                    .logType(LogType.BUSINESS_ERROR)
                    .data(Map.of("username", signInRequest.getUsername()))
                    .build());
        }
    }

}
