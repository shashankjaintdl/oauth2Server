package com.ics.icsoauth2server.api.blogs;

import lombok.Data;

public @Data class BlogCreationResponse extends BlogBaseRequest{
    private Long id;
    private String UUID;
    private String question;
    private String content;
}
