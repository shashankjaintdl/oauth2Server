package com.ics.icsoauth2server.api.roles;


import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.api.roles.repository.RolesRepository;
import com.ics.icsoauth2server.api.roles.services.RolesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController @RequiredArgsConstructor
@RequestMapping(RolesController.ENDPOINT)
public class RolesController {

    public static final String ENDPOINT = "/roles/";

    private final RolesService service;
    private APIResponse apiResponse;

    @GetMapping("/all")
    ResponseEntity<?> getAllRoles(HttpServletRequest request){
        apiResponse = new APIResponse(HttpStatus.OK.value(),HttpStatus.OK.toString(),"Fetched",service.listRoles(),request);
        return ResponseEntity.status(apiResponse.getStatusCode()).body(apiResponse);
    }


}
