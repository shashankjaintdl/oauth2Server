package com.ics.icsoauth2server.api.tag.services;

import com.ics.icsoauth2server.domain.Tags;
import com.ics.icsoauth2server.http.APIResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface TagService {

    ResponseEntity<APIResponse<Tags>> getTagByName(String name, HttpServletRequest httpServletRequest);
    ResponseEntity<APIResponse<Tags>> getAllTags(HttpServletRequest httpServletRequest);

}
