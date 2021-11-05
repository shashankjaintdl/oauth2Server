package com.ics.icsoauth2server.api.subscriber;

import com.ics.icsoauth2server.domain.request.BaseRequest;
import lombok.Data;

public @Data class SubscriberResponse extends BaseRequest {
    private Long id;
    private String UUID;
    private String firstName;
    private String lastName;
    private String emailId;
    private String message;
}
