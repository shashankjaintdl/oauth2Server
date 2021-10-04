package com.ics.icsoauth2server.api.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserRegistrationRequest implements Serializable {

    private UUID uuid;
    private String username;
    private String emailId;
    private String password;
    private String firstName;
    private String lastName;

    public UserRegistrationRequest(UUID uuid, String username, String emailId, String password, String firstName, String lastName){
        this.uuid = uuid;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
    }

}
