package com.ics.icsoauth2server.api.user;

import com.ics.icsoauth2server.api.user.service.UserService;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

import static com.ics.icsoauth2server.security.config.Roles.*;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@RequestMapping(
        value = UserController.ENDPOINT,
        consumes = {APPLICATION_JSON_VALUE},
        produces = {APPLICATION_JSON_VALUE}
)
public class UserController {

    protected final static String ENDPOINT = "/account";
    protected final static String USERNAME = "username";

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @PreAuthorize("isAnonymous()")
    @PostMapping("/create/user")
    public ResponseEntity<APIResponse<UserRegisterResponse>> createUser(@RequestBody UserRegistrationRequest registrationRequest,
                                                                        HttpServletRequest httpServletRequest) throws URISyntaxException {
        return userService.addUser(registrationRequest,ROLE_USER.getValue(),httpServletRequest);
    }

    @PreAuthorize("hasRole('ROLE_AD')")
    @PostMapping("/create/manager")
    public ResponseEntity<APIResponse<UserRegisterResponse>> createManagerAccount(@RequestBody UserRegistrationRequest registrationRequest,
                                                                                  HttpServletRequest httpServletRequest) throws URISyntaxException{
        return userService.addUser(registrationRequest,ROLE_MGR.getValue(),httpServletRequest);
    }

    @PreAuthorize("isAnonymous()")
    @PutMapping( "/profile/update/user/{username}")
    public ResponseEntity<APIResponse<UserRegisterResponse>> updateProfile(@RequestBody UserUpdateRequest userUpdateRequest,
                                                                           @PathVariable(name = USERNAME) String username,
                                                                           UserPrincipal userPrincipal,
                                                                           HttpServletRequest httpServletRequest) throws URISyntaxException {
        if(!userPrincipal.getUsername().equalsIgnoreCase(username)){
            throw new IllegalStateException("Not Authorized");
        }
        return userService.updateUser(userUpdateRequest,userPrincipal,httpServletRequest);
    }
}
