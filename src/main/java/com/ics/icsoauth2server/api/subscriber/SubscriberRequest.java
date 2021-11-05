package com.ics.icsoauth2server.api.subscriber;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import static com.ics.icsoauth2server.helper.ConstraintValidationMessage.*;

public @Data class SubscriberRequest {

    @NotNull(message = UUID_NOT_NULL)
    @NotEmpty(message = UUID_NOT_EMPTY)
    @NotBlank(message = UUID_NOT_BLANK)
    private String UUID;

    @NotNull(message = FIRST_NAME_NOT_NULL)
    @NotEmpty(message = FIRST_NAME_NOT_EMPTY)
    @NotBlank(message = FIRST_NAME_NOT_BLANK)
    private String firstName;

    @NotNull(message = LAST_NAME_NOT_NULL)
    @NotEmpty(message = LAST_NAME_NOT_EMPTY)
    @NotBlank(message = LAST_NAME_NOT_BLANK)
    private String lastName;

    @Email(message = EMAIL_NOT_VALID)
    @NotNull(message = EMAIL_NOT_NULL)
    @NotEmpty(message = EMAIL_NOT_EMPTY)
    @NotBlank(message = EMAIL_NOT_BLANK)
    private String emailId;

    @NotNull(message = MESSAGE_NOT_NULL)
    @NotEmpty(message = MESSAGE_NOT_EMPTY)
    @NotBlank(message = MESSAGE_NOT_BLANK)
    private String message;

}
