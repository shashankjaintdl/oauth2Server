package com.ics.icsoauth2server.api.blogs.mapper;

import com.ics.icsoauth2server.api.blogs.BlogCreationResponse;
import com.ics.icsoauth2server.domain.Blog;

public class BlogMapper {

    public BlogCreationResponse mapResponse(Blog blog){
        BlogCreationResponse response = new BlogCreationResponse();
        return response;
    }
}
