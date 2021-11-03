package com.ics.icsoauth2server.api.blogs;


import com.ics.icsoauth2server.api.blogs.service.BlogService;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.ics.icsoauth2server.helper.ConstantMessage.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = BlogController.ENDPOINT,
        consumes = {APPLICATION_JSON_VALUE},
        produces = {APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
public class BlogController {

    protected final static String ENDPOINT = API_VERSION+"/blog";

    private final BlogService blogService;

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<APIResponse<BlogCreationResponse>> create(@RequestBody BlogCreationRequest request,
                                                                    HttpServletRequest httpServletRequest,
                                                                    UserPrincipal userPrincipal){
        return blogService.create(request,httpServletRequest,userPrincipal);
    }


}
