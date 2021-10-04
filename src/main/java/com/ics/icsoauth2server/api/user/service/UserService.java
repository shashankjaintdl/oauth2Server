package com.ics.icsoauth2server.api.user.service;

import com.ics.icsoauth2server.api.user.UserRegistrationRequest;
import com.ics.icsoauth2server.api.user.UserRegisterResponse;
import com.ics.icsoauth2server.http.APIResponse;

public interface UserService {

    APIResponse<UserRegisterResponse> addUser(UserRegistrationRequest request);


}

