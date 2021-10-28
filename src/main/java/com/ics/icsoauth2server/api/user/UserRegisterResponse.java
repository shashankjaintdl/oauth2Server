package com.ics.icsoauth2server.api.user;

import com.ics.icsoauth2server.domain.Roles;
import lombok.Data;

import java.util.Set;

public @Data class UserRegisterResponse {
    private String UUID;
    private String firstName;
    private String lastName;
    private String username;
    private String emailId;
    private String phoneNo;
    private Set<Roles> role;
    private String profilePictureUrl;
}
