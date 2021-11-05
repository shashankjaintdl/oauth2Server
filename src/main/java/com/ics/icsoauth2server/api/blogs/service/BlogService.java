package com.ics.icsoauth2server.api.blogs.service;

import com.ics.icsoauth2server.api.blogs.BlogCreationRequest;
import com.ics.icsoauth2server.api.blogs.BlogCreationResponse;
import com.ics.icsoauth2server.api.blogs.BlogUpdateRequest;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

public interface BlogService {

    ResponseEntity<APIResponse<BlogCreationResponse>> savePost(BlogCreationRequest request, HttpServletRequest httpServletRequest, UserPrincipal principal) throws URISyntaxException;

    ResponseEntity<APIResponse<BlogCreationResponse>> publishPost(String title,HttpServletRequest httpServletRequest, UserPrincipal principal);

    ResponseEntity<APIResponse<BlogCreationResponse>> editPost(BlogUpdateRequest request, HttpServletRequest httpServletRequest, UserPrincipal principal);

    ResponseEntity<APIResponse<BlogCreationResponse>> deletePost(String title, HttpServletRequest httpServletRequest, UserPrincipal principal);

    ResponseEntity<APIResponse<BlogCreationResponse>> getPublishedPostByTitle(String title,HttpServletRequest httpServletRequest);

    ResponseEntity<APIResponse<BlogCreationResponse>> getAllPublishedPost(String sortBy, String sortOrder, Integer currentPage, Integer itemPerPage,HttpServletRequest httpServletRequest);

    ResponseEntity<APIResponse<BlogCreationResponse>> getAllUnPublishedPost(String sortBy, String sortOrder, Integer currentPage, Integer itemPerPage,HttpServletRequest httpServletRequest,UserPrincipal principal);

    Boolean existById(Long id, HttpServletRequest httpServletRequest);

    Boolean existByTopic(String question, HttpServletRequest httpServletRequest);

}
