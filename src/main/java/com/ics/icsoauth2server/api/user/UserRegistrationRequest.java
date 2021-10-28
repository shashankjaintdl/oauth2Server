package com.ics.icsoauth2server.api.user;

import com.ics.icsoauth2server.api.user.mapper.UserMapper;
import lombok.Data;

import java.io.Serializable;
import java.util.UUID;

@Data
public class UserRegistrationRequest implements Serializable {

    private String username;
    private String emailId;
    private String mobileNo;
    private String password;
    private String firstName;
    private String lastName;

    public UserRegistrationRequest(){}

    public UserRegistrationRequest( String username, String emailId, String password, String firstName, String lastName,String mobileNo){
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.emailId = emailId;
        this.mobileNo = mobileNo;
    }


}
