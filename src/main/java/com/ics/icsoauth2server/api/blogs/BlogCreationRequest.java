package com.ics.icsoauth2server.api.blogs;

import lombok.Data;

public @Data class BlogCreationRequest extends BlogBaseRequest{
    private String UUID;
    private String question;
    private String content;
}
