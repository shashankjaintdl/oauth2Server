package com.ics.icsoauth2server.api.blogs.mapper;

import com.ics.icsoauth2server.api.blogs.BlogCreationRequest;
import com.ics.icsoauth2server.api.blogs.BlogCreationResponse;
import com.ics.icsoauth2server.domain.Blog;

public class BlogMapper {



    public BlogCreationResponse mapResponse(Blog blog){
        BlogCreationResponse response = new BlogCreationResponse();
        response.setId(blog.getId());
        response.setUUID(blog.getUUID());
        response.setQuestion(blog.getTopic());
        response.setContent(blog.getContents());
        response.setCreatedBy(blog.getCreatedBy());
        response.setCreatedDate(blog.getCreatedDate());
        response.setUpdatedDate(blog.getUpdatedDate());
        return response;
    }
}
