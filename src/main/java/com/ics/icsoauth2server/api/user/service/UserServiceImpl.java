package com.ics.icsoauth2server.api.user.service;

import com.ics.icsoauth2server.api.permissions.repository.PermissionRepository;
import com.ics.icsoauth2server.api.user.UserRegistrationRequest;
import com.ics.icsoauth2server.api.user.UserRegisterResponse;
import com.ics.icsoauth2server.api.user.repository.UserRepository;
import com.ics.icsoauth2server.domain.User;
import com.ics.icsoauth2server.http.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.annotation.PostConstruct;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final IdGenerator idGenerator;
    private final PasswordEncoder passwordEncoder;
    private final PermissionRepository permissionRepository;

    @PostConstruct
    public void postConstruct(){
        userRepository.save(new User(idGenerator.generateId().toString(),"sjain","jain","sjaintdl"+ idGenerator.generateId(),"sjain@gmail.com"+idGenerator.generateId(),passwordEncoder.encode("password")));

    }

    @Override
    public APIResponse<UserRegisterResponse> addUser(UserRegistrationRequest request) {
        return null;
    }




}
