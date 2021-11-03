package com.ics.icsoauth2server.api.blogs.service;

import com.ics.icsoauth2server.api.blogs.BlogCreationRequest;
import com.ics.icsoauth2server.api.blogs.BlogCreationResponse;
import com.ics.icsoauth2server.api.blogs.repository.BlogRepository;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl  implements BlogService{

    private final BlogRepository blogRepository;


    @Override
    public ResponseEntity<APIResponse<BlogCreationResponse>> create(BlogCreationRequest request, HttpServletRequest httpServletRequest, UserPrincipal principal) {
        return null;
    }


    @Override
    public Boolean existById(Long id, HttpServletRequest httpServletRequest) {
        return null;
    }

    @Override
    public Boolean existByQuestion(String question, HttpServletRequest httpServletRequest) {
        return null;
    }


}
