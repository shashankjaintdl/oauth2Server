package com.ics.icsoauth2server.api.blogs.service;

import com.ics.icsoauth2server.api.blogs.BlogCreationRequest;
import com.ics.icsoauth2server.api.blogs.BlogCreationResponse;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface BlogService {

    ResponseEntity<APIResponse<BlogCreationResponse>> create(BlogCreationRequest request, HttpServletRequest httpServletRequest, UserPrincipal principal);

    Boolean existById(Long id, HttpServletRequest httpServletRequest);

    Boolean existByQuestion(String question, HttpServletRequest httpServletRequest);


}
