package com.ics.icsoauth2server.api.user;

import com.ics.icsoauth2server.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(UserController.ENDPOINT)
@RequiredArgsConstructor
public class UserController {

    protected final static String ENDPOINT = "/user";

    private final UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<UserRegisterResponse> createUser(@RequestBody UserRegistrationRequest registrationRequest,
                                                           HttpServletRequest httpServletRequest){
        System.out.println(httpServletRequest);
        return null;
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUser(){
        return ResponseEntity.ok(userRepository.findAll());
    }


}
