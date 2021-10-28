package com.ics.icsoauth2server.api.user.mapper;


import com.ics.icsoauth2server.api.user.UserRegisterResponse;
import com.ics.icsoauth2server.api.user.UserRegistrationRequest;
import com.ics.icsoauth2server.api.user.UserUpdateRequest;
import com.ics.icsoauth2server.domain.Permissions;
import com.ics.icsoauth2server.domain.Roles;
import com.ics.icsoauth2server.domain.User;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

public class UserMapper {

    public User requestMap(UserRegistrationRequest request, Set<Roles> roles,String password){
        User entity = new User();
        entity.setUuid(UUID.randomUUID().toString());
        entity.setUsername(request.getUsername());
        entity.setEmailId(request.getEmailId());
        entity.setFirstName(request.getFirstName());
        entity.setLastName(request.getLastName());
        entity.setUpdatedDate(new Date());
        entity.setMobileNo(request.getMobileNo());
        entity.setPassword(password);
        entity.setRoles(roles);
        return entity;
    }

    public User updateUserRequestMap(UserUpdateRequest request){

        User entity = new User();

        entity.setUpdatedDate(new Date());
        if(!request.getFirstName().isEmpty()) {
            entity.setFirstName(request.getFirstName());
        }
        if(!request.getLastName().isEmpty())
        {
            entity.setLastName(request.getLastName());
        }
        if(!request.getEmailId().isEmpty())
        {
            entity.setEmailId(request.getEmailId());
        }
//        if(!request.getProfilePicURL().isEmpty()){
//            entity.
//        }
        return entity;
    }

    public UserRegisterResponse userRegisterResponseMap(User user){
        UserRegisterResponse response = new UserRegisterResponse();
        response.setUUID(user.getUuid());
        response.setUsername(user.getUsername());
        response.setRole(user.getRoles());
        response.setPhoneNo(user.getMobileNo());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmailId(user.getEmailId());
        return response;
    }


}
