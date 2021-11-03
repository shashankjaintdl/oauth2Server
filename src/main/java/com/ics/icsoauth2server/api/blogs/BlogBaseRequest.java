package com.ics.icsoauth2server.api.blogs;

import lombok.Data;

import java.util.Date;

public @Data class BlogBaseRequest {
    private Date createdDate;
    private Date updatedDate;
    private String username;
}
