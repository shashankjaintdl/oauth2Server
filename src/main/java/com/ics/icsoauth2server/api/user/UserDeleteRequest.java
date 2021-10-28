package com.ics.icsoauth2server.api.user;

import lombok.Data;

public @Data class UserDeleteRequest {

    private Long id;
    private String UUID;
    private String username;
    private String emailId;

}
