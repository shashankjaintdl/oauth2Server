package com.ics.icsoauth2server.api.user;

import lombok.Data;

import javax.validation.constraints.*;
import java.io.Serializable;

import static com.ics.icsoauth2server.helper.ConstraintValidationMessage.*;

@Data
public class UserRegistrationRequest implements Serializable {

    @NotNull(message = USERNAME_NOT_NULL)
    @NotEmpty(message = USERNAME_NOT_EMPTY)
    @NotBlank(message = USERNAME_NOT_BLANK)
    private String username;

    @Email(message = EMAIL_NOT_VALID)
    @NotNull(message = EMAIL_NOT_NULL)
    @NotEmpty(message = EMAIL_NOT_EMPTY)
    @NotBlank(message = EMAIL_NOT_BLANK)
    private String emailId;

    @Max(message = "",value = 12)
    private String mobileNo;

    @NotNull(message = PASSWORD_NOT_NULL)
    @NotEmpty(message = PASSWORD_NOT_EMPTY)
    @NotBlank(message = PASSWORD_NOT_BLANK)
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
