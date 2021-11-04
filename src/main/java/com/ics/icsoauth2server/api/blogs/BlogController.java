package com.ics.icsoauth2server.api.blogs;


import com.ics.icsoauth2server.api.blogs.repository.BlogRepository;
import com.ics.icsoauth2server.api.blogs.service.BlogService;
import com.ics.icsoauth2server.domain.Blog;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.net.URISyntaxException;
import java.util.List;

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
    private final BlogRepository blogRepository;
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<APIResponse<BlogCreationResponse>> create(@RequestBody @Valid BlogCreationRequest request,
                                                                    HttpServletRequest httpServletRequest,
                                                                    UserPrincipal userPrincipal) throws URISyntaxException {
        return blogService.createBlog(request,httpServletRequest,userPrincipal);
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/publish/{id}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> publish(@PathVariable(name = "id") Long id,
                                                                      HttpServletRequest httpServletRequest,
                                                                      UserPrincipal principal){
        return blogService.publishBlog(id,httpServletRequest,principal);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{topic}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getBlogForPublish(@PathVariable(name = "topic") String topic,
                                                                               HttpServletRequest httpServletRequest){
        return null;
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/un-published/all")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getAllUnPublishBlog(HttpServletRequest httpServletRequest,
                                                                                 UserPrincipal userPrincipal){

        return null;
    }


}
