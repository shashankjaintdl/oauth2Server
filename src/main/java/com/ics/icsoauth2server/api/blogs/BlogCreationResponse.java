package com.ics.icsoauth2server.api.blogs;

import lombok.Data;

import java.util.Set;

public @Data class BlogCreationResponse extends BlogBaseRequest{
    private Long id;
    private String UUID;
    private String title;
    private String content;
    private Boolean isPublished;
    private Boolean isDeleted;
    private Set<String> tags;
}
