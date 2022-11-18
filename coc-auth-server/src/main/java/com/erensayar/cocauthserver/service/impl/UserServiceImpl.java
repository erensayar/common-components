package com.erensayar.cocauthserver.service.impl;

import com.erensayar.cocauthserver.model.request.SignupRequest;
import com.erensayar.cocauthserver.repository.UserRepository;
import com.erensayar.cocauthserver.service.UserService;
import com.erensayar.core.error.exception.BadRequestException;
import com.erensayar.core.error.exception.NoContentException;
import com.erensayar.core.log.model.dto.LogModel;
import com.erensayar.core.log.model.enums.LogType;
import com.erensayar.core.util.service.MailUtilService;
import com.erensayar.cocauthserver.exception.Exceptions;
import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.model.enums.Role;
import com.erensayar.cocauthserver.model.request.UserRequest;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.ReflectionUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailUtilService mailUtilService;


    @Override
    @Transactional
    public User loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() ->
                new UsernameNotFoundException("User not available with this username: " + username));
    }


    @Override
    public User save(SignupRequest signUpRequest) {
        User user = User.builder()
                .username(signUpRequest.getUsername())
                .email(signUpRequest.getEmail())
                .password(passwordEncoder.encode(signUpRequest.getPassword()))
                .roles(new HashSet<>(signUpRequest.getRoles()))
                .build();
        return userRepository.save(user);
    }


    @Override
    public User findById(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new NoContentException(LogModel.builder()
                .apiError(Exceptions.USER_NO_CONTENT_BY_ID)
                .logType(LogType.WARNING)
                .data(Map.of("Id", id.toString()))
                .build()));
    }


    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(() -> new NoContentException(LogModel.builder()
                .apiError(Exceptions.USER_NO_CONTENT_BY_EMAIL)
                .logType(LogType.WARNING)
                .data(Map.of("Email", email))
                .build()));
    }


    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }


    @Override
    public User update(UUID id, UserRequest userRequest) {
        User user = checkIsPresent(id);
        String newEmail = userRequest.getEmail();
        if (newEmail != null && !user.getEmail().equals(newEmail)) {
            mailUtilService.sendConfirmMail(newEmail);
            user.setMailVerification(false);
            user.setEmail(userRequest.getEmail());
        }
        user.setId(user.getId());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setUsername(userRequest.getUsername());
        return userRepository.save(user);
    }


    @Override
    public User patch(UUID id, Map<String, Object> fields) {
        User user = checkIsPresent(id);
        fields.forEach((key, value) -> {
            Field field = ReflectionUtils.findRequiredField(User.class, key);
            field.setAccessible(true);
            if(key.equals("email") && value != null && !user.getEmail().equals(value)){
                mailUtilService.sendConfirmMail((String) value);
                user.setMailVerification(false);
            }
            ReflectionUtils.setField(field, user, value);
        });
        return userRepository.save(user);
    }


    @Override
    public void deleteById(UUID id) {
        userRepository.findById(id).orElseThrow(() -> new BadRequestException(LogModel.builder()
                .apiError(Exceptions.USER_NO_CONTENT_BY_ID)
                .logType(LogType.WARNING)
                .data(Map.of("Id", id.toString()))
                .build()));
        userRepository.deleteById(id);
    }


    @Override
    public List<User> saveAll(List<User> users) {
        return userRepository.saveAll(users);
    }


    // PRIVATE
    // <=================================================================================================================>


    private User checkIsPresent(UUID id) {
        return userRepository.findById(id).orElseThrow(() -> new BadRequestException(LogModel.builder()
                .apiError(Exceptions.USER_NO_CONTENT_BY_ID)
                .logType(LogType.WARNING)
                .data(Map.of("Id", id.toString()))
                .build()));
    }


}
