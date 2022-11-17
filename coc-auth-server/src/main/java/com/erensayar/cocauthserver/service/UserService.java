package com.erensayar.cocauthserver.service;

import com.erensayar.cocauthserver.model.entity.User;
import com.erensayar.cocauthserver.model.request.SignupRequest;
import com.erensayar.cocauthserver.model.request.UserRequest;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface UserService {

    List<User> findAll();

    User findById(UUID id);

    User save(SignupRequest signupRequest);

    User update(UUID id, UserRequest userRequest);

    User patch(UUID id, Map<String, Object> fields);

    void deleteById(UUID id);

    User findByEmail(String email);

    List<User> saveAll(List<User> accounts);

}