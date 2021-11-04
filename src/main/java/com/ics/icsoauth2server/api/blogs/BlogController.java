package com.ics.icsoauth2server.api.blogs;


import com.ics.icsoauth2server.api.blogs.service.BlogService;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.net.URISyntaxException;

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
    public ResponseEntity<APIResponse<BlogCreationResponse>> create(@RequestBody @Valid BlogCreationRequest request,
                                                                    HttpServletRequest httpServletRequest,
                                                                    UserPrincipal userPrincipal) throws URISyntaxException {
        return blogService.savePost(request,httpServletRequest,userPrincipal);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/publish/{title}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> publish(@PathVariable(name = "title")  String title,
                                                                      HttpServletRequest httpServletRequest,
                                                                      UserPrincipal principal){
        return blogService.publishPost(title,httpServletRequest,principal);
    }


    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update-post")
    public ResponseEntity<APIResponse<BlogCreationResponse>> updatePost(@RequestBody BlogUpdateRequest request,
                                                                        HttpServletRequest httpServletRequest,
                                                                        UserPrincipal principal){
        return blogService.editPost(request,httpServletRequest,principal);
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{topic}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getUnPublishPost(@PathVariable(name = "title") String title,
                                                                               HttpServletRequest httpServletRequest){
        return null;
    }


    @PreAuthorize("isAuthenticated()")
    @GetMapping("/un-published/all")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getAllUnPublishBlog(HttpServletRequest httpServletRequest,
                                                                                 UserPrincipal userPrincipal){
        return null;
    }


    @GetMapping("/published/{title}")
    public ResponseEntity<?> getBlogById(@PathVariable(name = "title") String title){
        return null;
    }


    @GetMapping("/published/all")
    public ResponseEntity<?> getAllPublishBlog(){
        return null;
    }




}
