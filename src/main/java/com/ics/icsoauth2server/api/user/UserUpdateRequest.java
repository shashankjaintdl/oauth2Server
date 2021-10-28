package com.ics.icsoauth2server.api.user;


import lombok.Data;

import java.io.Serializable;

public @Data class UserUpdateRequest implements Serializable {

    private String username;
    private String firstName;
    private String lastName;
    private String emailId;
    private String profilePic;
}
