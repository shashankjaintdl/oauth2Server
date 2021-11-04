package com.ics.icsoauth2server.api.blogs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
public  @Data  class BlogUpdateRequest extends BlogBaseRequest{
    private String title;
    private String content;
    private Set<String> tags;

}
