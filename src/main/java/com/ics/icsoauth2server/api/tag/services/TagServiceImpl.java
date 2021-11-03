package com.ics.icsoauth2server.api.tag.services;

import com.ics.icsoauth2server.api.tag.exceptions.TagNotFoundExceptions;
import com.ics.icsoauth2server.api.tag.repository.TagRepository;
import com.ics.icsoauth2server.domain.Tags;
import com.ics.icsoauth2server.exception.InternalServerException;
import com.ics.icsoauth2server.http.APIResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements TagService{

    private final TagRepository tagRepository;
    private APIResponse apiResponse;
    private List<Object> tagsList;

    private final static Logger LOGGER = LoggerFactory.getLogger(TagServiceImpl.class);

    @Override
    public ResponseEntity<APIResponse<Tags>> getTagByName(String name, HttpServletRequest httpServletRequest) {
        tagsList = new ArrayList<>();
        Tags tags = tagRepository.findByTagName(name)
                .orElseThrow(
                        ()-> new TagNotFoundExceptions("Tag not found by name = "+name)
                );
        tagsList.add(tags);
        apiResponse = new APIResponse<>(OK.value(), OK.toString(),"Success Fetched",tagsList,httpServletRequest);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }

    @Override
    public ResponseEntity<APIResponse<Tags>> getAllTags(HttpServletRequest httpServletRequest) {
        LOGGER.info("Fetching all tags");
        List<Tags> tags;
        try {
           tags = tagRepository.findAll();
        }
        catch (Exception ex){
            throw new InternalServerException("");
        }
        if(tags.isEmpty()){
            LOGGER.info("Tags are empty!");
            throw new TagNotFoundExceptions("Tags are empty!");
        }
        apiResponse = new APIResponse<>(OK.value(), OK.toString(),"Success Fetched",tags,httpServletRequest);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }
}
