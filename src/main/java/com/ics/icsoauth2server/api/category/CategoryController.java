package com.ics.icsoauth2server.api.category;

import com.ics.icsoauth2server.exception.UnauthorizedUserException;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.http.MediaType.*;

@RestController
@RequestMapping(
        value = CategoryController.ENDPOINT,
        consumes = {APPLICATION_JSON_VALUE},
        produces = {APPLICATION_JSON_VALUE}
)
public class CategoryController {

    protected final static String ENDPOINT = "/category";

    @PreAuthorize("hasRole('ROLE_AD')")
    @PostMapping("/create")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request, HttpServletRequest httpServletRequest){

        return null;
    }





}
