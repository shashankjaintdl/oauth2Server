package com.ics.icsoauth2server.api.tag;

import com.ics.icsoauth2server.api.tag.services.TagService;
import com.ics.icsoauth2server.domain.Tags;
import com.ics.icsoauth2server.http.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static com.ics.icsoauth2server.helper.ConstantMessage.*;
import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(
        value = TagController.ENDPOINT,
        consumes = {APPLICATION_JSON_VALUE},
        produces = {APPLICATION_JSON_VALUE}
)
public class TagController {

    protected final static String ENDPOINT = API_VERSION+"/tags";

    private final TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }


    @PreAuthorize("hasRole('ROLE_AD')")
    @PostMapping("/create")
    public ResponseEntity<TagResponse> create(@RequestBody TagRequest request, HttpServletRequest httpServletRequest){
        return null;
    }


    @GetMapping("/all")
    public ResponseEntity<APIResponse<Tags>> getAllTag(HttpServletRequest httpServletRequest){
        return tagService.getAllTags(httpServletRequest);
    }

    @GetMapping("/{name}")
    public ResponseEntity<APIResponse<Tags>> getTagById(@PathVariable(name = "name") String name, HttpServletRequest httpServletRequest){
        return tagService.getTagByName(name,httpServletRequest);
    }





}
