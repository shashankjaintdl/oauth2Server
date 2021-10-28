package com.ics.icsoauth2server.api.user.service;

import com.ics.icsoauth2server.api.user.UserRegistrationRequest;
import com.ics.icsoauth2server.api.user.UserRegisterResponse;
import com.ics.icsoauth2server.http.APIResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

public interface UserService {

    ResponseEntity<APIResponse<UserRegisterResponse>> addUser(UserRegistrationRequest request, Long id,HttpServletRequest httpServletRequest) throws URISyntaxException;

    boolean userExistByUsername(String username);
    boolean userExistByMobileNo(String mobileNo);
    boolean userExistByEmailId(String emailId);
    void userExist(UserRegistrationRequest request);

}

