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

    /**
     * Creating new post
     * @param request
     * @param httpServletRequest
     * @param userPrincipal
     * @return
     * @throws URISyntaxException
     **/
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public ResponseEntity<APIResponse<BlogCreationResponse>> create(@RequestBody @Valid BlogCreationRequest request,
                                                                    HttpServletRequest httpServletRequest,
                                                                    UserPrincipal userPrincipal) throws URISyntaxException {
        return blogService.savePost(request,httpServletRequest,userPrincipal);
    }


    /**
     * Publishing post which is not published
     * @param title
     * @param httpServletRequest
     * @param principal
     * @return
     **/
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/publish/{title}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> publish(@PathVariable(name = "title")  String title,
                                                                     HttpServletRequest httpServletRequest,
                                                                     UserPrincipal principal){
        return blogService.publishPost(title,httpServletRequest,principal);
    }

    /**
     *
     *  Updating the post
     * @param request send update request to update the content of post
     * @param httpServletRequest
     * @param principal: User principal
     * @return
     *
     *
     **/
    @PreAuthorize("isAuthenticated()")
    @PutMapping("/update-post")
    public ResponseEntity<APIResponse<BlogCreationResponse>> updatePost(@RequestBody BlogUpdateRequest request,
                                                                        HttpServletRequest httpServletRequest,
                                                                        UserPrincipal principal){
        return blogService.editPost(request,httpServletRequest,principal);
    }

    /**
     *
     * @param title
     * @param httpServletRequest
     * @param principal
     * @return
     **/
    @PreAuthorize("isAuthenticated()")
    @DeleteMapping("/delete/{title}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> delete(@PathVariable(name = "title") String title,
                                                                    HttpServletRequest httpServletRequest,
                                                                    UserPrincipal principal){
        return blogService.deletePost(title,httpServletRequest,principal);
    }

    /**
     *
     * @param title
     * @param httpServletRequest
     * @param userPrincipal
     * @return
     */
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/{title}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getUnPublishPost(@PathVariable(name = "title") String title,
                                                                              HttpServletRequest httpServletRequest,
                                                                              UserPrincipal userPrincipal){
        return null;
    }

    /**
     *
     * @param httpServletRequest
     * @param userPrincipal
     * @return
     */

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/un-published/all")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getAllUnPublishBlog(@RequestParam(name = "sortBy", defaultValue = "",required = false) String sortBy,
                                                                                 @RequestParam(name ="sortOrder", defaultValue = "",required = false) String sortOrder,
                                                                                 @RequestParam(name = "currentPage", defaultValue = "1",required = false) Integer currentPage,
                                                                                 @RequestParam(name = "itemsPerPage", defaultValue = "10", required = false) Integer itemsPerPage,
                                                                                 HttpServletRequest httpServletRequest,
                                                                                 UserPrincipal userPrincipal){
        return blogService.getAllUnPublishedPost(sortBy,sortOrder,currentPage,itemsPerPage,httpServletRequest,userPrincipal);
    }


    /**
     *  No authentication required to complete view or reading the post.
     * @param title
     * @param httpServletRequest
     * @return
     */

    @GetMapping("/published-post/{title}")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getPostByTitle(@PathVariable(name = "title") String title,
                                                                            HttpServletRequest httpServletRequest){
        return blogService.getPublishedPostByTitle(title,httpServletRequest);
    }

    /**
     *
     * @return
     */
    @GetMapping("/published/all")
    public ResponseEntity<APIResponse<BlogCreationResponse>> getAllPublishBlog(@RequestParam(name = "sortBy", defaultValue = "",required = false) String sortBy,
                                                                               @RequestParam(name ="sortOrder", defaultValue = "",required = false) String sortOrder,
                                                                               @RequestParam(name = "currentPage", defaultValue = "1",required = false) Integer currentPage,
                                                                               @RequestParam(name = "itemsPerPage", defaultValue = "10", required = false) Integer itemsPerPage,
                                                                               HttpServletRequest httpServletRequest){
        return blogService.getAllPublishedPost(sortBy,sortOrder,currentPage,itemsPerPage,httpServletRequest);
    }

}
