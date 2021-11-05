package com.ics.icsoauth2server.api.blogs.service;

import com.ics.icsoauth2server.api.blogs.BlogCreationRequest;
import com.ics.icsoauth2server.api.blogs.BlogCreationResponse;
import com.ics.icsoauth2server.api.blogs.BlogUpdateRequest;
import com.ics.icsoauth2server.api.blogs.exception.BlogAlreadyExistException;
import com.ics.icsoauth2server.api.blogs.exception.BlogNotFoundException;
import com.ics.icsoauth2server.api.blogs.mapper.BlogMapper;
import com.ics.icsoauth2server.api.blogs.repository.BlogRepository;
import com.ics.icsoauth2server.api.user.repository.UserRepository;
import com.ics.icsoauth2server.domain.Blog;
import com.ics.icsoauth2server.exception.InternalServerException;
import com.ics.icsoauth2server.exception.PermissionDeniedException;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import com.ics.icsoauth2server.utils.RolePermissionUtils;
import com.ics.icsoauth2server.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

import static com.ics.icsoauth2server.helper.ConstantExceptionMessage.*;
import static com.ics.icsoauth2server.helper.ConstantMessage.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl  implements BlogService{


    private final static Logger LOGGER = LoggerFactory.getLogger(BlogServiceImpl.class);

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final IdGenerator idGenerator;

    private BlogMapper mapper = new BlogMapper();
    private APIResponse<BlogCreationResponse> apiResponse;
    private List<BlogCreationResponse> blogCreationResponses;

    @Override
    public ResponseEntity<APIResponse<BlogCreationResponse>> savePost(BlogCreationRequest request,
                                                                        HttpServletRequest httpServletRequest,
                                                                        UserPrincipal principal) throws URISyntaxException {
        if(existByTopic(request.getTitle(),httpServletRequest)){
            LOGGER.info(POST_ALREADY_EXIST+" with title ==> {}",request.getTitle());
            throw new BlogAlreadyExistException(POST_ALREADY_EXIST);
        }


        Blog blog = new Blog(
                idGenerator.generateId().toString(),
                StringUtils.replaceSpaceWithUnderScore(request.getTitle()),
                request.getContent(),
                principal.getUsername(),
                request.getTags(),
                userRepository.findByUsername(principal.getUsername()).get()
        );

        blog.setIsDeleted(false);

        blog.setIsPublished(false);

        try {
            LOGGER.info("Saving the post in database with title ==> {}",request.getTitle());

            blogRepository.save(blog);
//
//            blogCreationResponses = new ArrayList<>();
//
//            blogCreationResponses.add(mapper.mapResponse(blog));

            LOGGER.info(POST_CREATED_SUCCESS+" with title ==> {}",blog.getTitle());

            apiResponse = new APIResponse(
                    CREATED.value(),
                    CREATED.toString(),
                    POST_CREATED_SUCCESS,
                    createResponses(blog),
                    httpServletRequest
            );
        }

        catch (Exception e){
            throw new InternalServerException(BLOG_CREATION_ERROR);
        }

        return ResponseEntity
                .created(new URI(apiResponse.getPath())).body(apiResponse);
    }


    @Override
    public ResponseEntity<APIResponse<BlogCreationResponse>> publishPost(String title,
                                                                         HttpServletRequest httpServletRequest,
                                                                         UserPrincipal principal) {

        Optional<Blog> blog = blogRepository.findByTitleAndCreatedBy(title,principal.getUsername());

        if(!blog.isPresent()){
            LOGGER.info(POST_NOT_EXIST+"with title ==> {}" ,title);
            throw new BlogNotFoundException(POST_NOT_EXIST);
        }

        if(blog.get().getIsDeleted()){
            LOGGER.info(POST_ALREADY_DELETED);
            throw new BlogNotFoundException(POST_ALREADY_DELETED);
        }

        /**
         *  Changing the status of Post from un-published to published to show it to the End-User;
         * */

        blog.get().setIsPublished(true);

        blog.get().setPublishedDate(new Date());


        try {
            LOGGER.info("Saving and publishing the post  in database ==> {}",blog.get().getTitle());

            blogRepository.save(blog.get());

            LOGGER.info(POST_PUBLISH_SUCCESS+" with title ==> {} ",blog.get().getTitle());

            apiResponse = new APIResponse<>(
                    OK.value(),
                    OK.toString(),
                    POST_PUBLISH_SUCCESS,
                    createResponses(blog.get()),
                    httpServletRequest
            );
        }

        catch (Exception e){
            throw new InternalServerException(POST_PUBLISH_ERROR);
        }

        return ResponseEntity
                .status(apiResponse.getStatusCode()).body(apiResponse);
    }


    @Override
    public ResponseEntity<APIResponse<BlogCreationResponse>> editPost(BlogUpdateRequest request, HttpServletRequest httpServletRequest, UserPrincipal principal) {

        Optional<Blog> blog = blogRepository.findByTitleAndCreatedBy(request.getTitle(),principal.getUsername());

        if(!blog.isPresent()){
            LOGGER.info(POST_NOT_EXIST+"with title ==> {}", request.getTitle());
            throw new BlogNotFoundException(POST_NOT_EXIST);
        }

        if(blog.get().getIsDeleted()){
            LOGGER.info(POST_ALREADY_DELETED);
            throw new BlogNotFoundException(POST_ALREADY_DELETED);
        }

        Set<String> tags = new HashSet<>();

        blog.get().setUpdatedDate(new Date());
        /**
         * Setting the post is un-published to publish it again after update.
         * */
        blog.get().setIsPublished(false);
        /**
         * Updating the post contents
         */

        blog.get().setContents(request.getContent());

        /**
         * Updating the tags without duplicating in each post
         */
        tags.addAll(request.getTags());
        tags.addAll(blog.get().getTags());
        blog.get().setTags(tags);

        try{
            LOGGER.info("Updating post in database with title ==> {}", request.getTitle());

            blogRepository.saveAndFlush(blog.get());
        }

        catch (Exception ex){
            throw new InternalServerException(POST_UPDATE_ERROR);
        }

        apiResponse = new APIResponse<>(
                OK.value(),
                OK.toString(),
                POST_UPDATE_SUCCESS,
                createResponses(blog.get()),
                httpServletRequest
        );

        return ResponseEntity
                .status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse<BlogCreationResponse>> deletePost(String title,HttpServletRequest httpServletRequest,UserPrincipal principal) {

        Optional<Blog> blog = blogRepository.findByTitle(title);
        Blog entity = new Blog();

        /**
         * Checking if post exist in database
         **/

        if(!blog.isPresent()) {
            LOGGER.info(POST_NOT_EXIST+" with title ==> {}",title);
            throw new BlogNotFoundException(POST_NOT_EXIST);
        }

        /**
         * Checking if post deleted
         **/
        if(blog.get().getIsDeleted()){
            LOGGER.info(POST_ALREADY_DELETED+"with title ==> {}",title);
            throw new BlogNotFoundException(POST_ALREADY_DELETED);
        }

        /**
         * Post owner deleting the post
         **/

        if(principal.getUsername().equals(blog.get().getCreatedBy()) && RolePermissionUtils.hasBlogDeletePermission(principal)){
            LOGGER.info("Post deleting by user [ "+principal.getUsername()+" ] with title ==> {}",blog.get().getTitle());
            entity = deletePost(blog.get());
        }

        /**
         * Admin can delete owner post
         **/

        else if(RolePermissionUtils.isAdmin(principal)){
            LOGGER.info("Post deleting by Admin with title ==> {}",blog.get().getTitle());
            entity = deletePost(blog.get());
        }

        /**
         * Denying permission for other users to perform delete operation
         **/

        else{
            LOGGER.info("Permission denied to perform this operation to the user ==> {} ",principal.getUsername());
            throw new PermissionDeniedException(PERMISSION_DENIED);
        }

        apiResponse = new APIResponse<>(
                OK.value(),
                OK.toString(),
                POST_DELETED_SUCCESS,
                createResponses(entity),
                httpServletRequest
        );

        return ResponseEntity
                .status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse<BlogCreationResponse>> getPublishedPostByTitle(String title, HttpServletRequest httpServletRequest) {

        Optional<Blog> blog = blogRepository.findByTitleAndIsPublishedAndIsDeleted(title,true,false);

        /**
         * Checking if post exist in database
         **/

        if(!blog.isPresent()) {
            LOGGER.info(POST_NOT_EXIST+" with title ==> {}",title);
            throw new BlogNotFoundException(POST_NOT_EXIST);
        }

        apiResponse =  new APIResponse<>(
                OK.value(),
                OK.toString(),
                POST_SUCCESS_FETCH,
                createResponses(blog.get()),
                httpServletRequest
        );
        return ResponseEntity
                .status(apiResponse.getStatusCode()).body(apiResponse);
    }

    protected List<BlogCreationResponse> createResponses(Blog blog){
        blogCreationResponses = new ArrayList<>();
        blogCreationResponses.add(mapper.mapResponse(blog));
        return blogCreationResponses;
    }

    protected Blog deletePost(Blog blog){
        try {
            blog.setIsDeleted(true);
            blog.setIsPublished(false);
            blogRepository.save(blog);
            LOGGER.info(POST_DELETED_SUCCESS);
        }
        catch (Exception ex){
            throw new InternalServerException(POST_DELETION_ERROR);
        }
        return blog;
    }

    @Override
    public Boolean existById(Long id, HttpServletRequest httpServletRequest) {
        return blogRepository.existsById(id);
    }

    @Override
    public Boolean existByTopic(String title, HttpServletRequest httpServletRequest) {
       return blogRepository.existsByTitle(title);
    }




}
