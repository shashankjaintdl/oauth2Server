package com.ics.icsoauth2server.domain.request;

import lombok.Data;

import java.util.Date;

public @Data class BaseRequest {

    private Date createdDate;

    private Date updatedDate;

}
