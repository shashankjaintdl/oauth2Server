package com.ics.icsoauth2server.api.blogs.service;

import com.ics.icsoauth2server.api.blogs.BlogCreationRequest;
import com.ics.icsoauth2server.api.blogs.BlogCreationResponse;
import com.ics.icsoauth2server.api.blogs.exception.BlogAlreadyExistException;
import com.ics.icsoauth2server.api.blogs.mapper.BlogMapper;
import com.ics.icsoauth2server.api.blogs.repository.BlogRepository;
import com.ics.icsoauth2server.api.user.repository.UserRepository;
import com.ics.icsoauth2server.domain.Blog;
import com.ics.icsoauth2server.exception.InternalServerException;
import com.ics.icsoauth2server.helper.ConstantMessage;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.ics.icsoauth2server.helper.ConstantMessage.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class BlogServiceImpl  implements BlogService{

    private final BlogRepository blogRepository;
    private final UserRepository userRepository;
    private final IdGenerator idGenerator;

    private BlogMapper mapper = new BlogMapper();
    private APIResponse<BlogCreationResponse> apiResponse;
    private List<BlogCreationResponse> blogCreationResponses;

    @Override
    public ResponseEntity<APIResponse<BlogCreationResponse>> createBlog(BlogCreationRequest request,
                                                                        HttpServletRequest httpServletRequest,
                                                                        UserPrincipal principal) throws URISyntaxException {
        if(existByTopic(request.getTopic(),httpServletRequest)){
            throw new BlogAlreadyExistException("Already Exist");
        }
        Blog blog = new Blog(
                idGenerator.generateId().toString(),
                request.getTopic(),
                request.getContent(),
                principal.getUsername(),
                request.getTags(),
                userRepository.findByUsername(principal.getUsername()).get()
        );
        try {
            blogRepository.save(blog);
            blogCreationResponses = new ArrayList<>();
            blogCreationResponses.add(mapper.mapResponse(blog));
            apiResponse = new APIResponse(
                    CREATED.value(),
                    CREATED.toString(),
                    BLOG_CREATED_SUCCESS,
                    blogCreationResponses,
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
    public ResponseEntity<APIResponse<BlogCreationResponse>> publishBlog(Long id,
                                                                         HttpServletRequest httpServletRequest,
                                                                         UserPrincipal principal) {
        // After publish the Blog check status if is published
        return null;
    }



    @Override
    public Boolean existById(Long id, HttpServletRequest httpServletRequest) {
        return blogRepository.existsById(id);
    }

    @Override
    public Boolean existByTopic(String topic, HttpServletRequest httpServletRequest) {
       return blogRepository.existsByTopic(topic);
    }


}
