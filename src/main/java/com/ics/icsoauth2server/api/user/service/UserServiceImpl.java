package com.ics.icsoauth2server.api.user.service;

import com.ics.icsoauth2server.api.roles.services.RolesService;
import com.ics.icsoauth2server.api.user.UserRegistrationRequest;
import com.ics.icsoauth2server.api.user.UserRegisterResponse;
import com.ics.icsoauth2server.api.user.UserUpdateRequest;
import com.ics.icsoauth2server.exception.InternalServerException;
import com.ics.icsoauth2server.exception.UserExistException;
import com.ics.icsoauth2server.api.user.mapper.UserMapper;
import com.ics.icsoauth2server.api.user.repository.UserRepository;
import com.ics.icsoauth2server.domain.User;
import com.ics.icsoauth2server.helper.ConstantMessage;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    private final IdGenerator idGenerator;
    private final RolesService rolesService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private List<UserRegisterResponse> list;

    private  APIResponse<UserRegisterResponse> apiResponse;

    private UserMapper mapper = new UserMapper();

    @Override
    public ResponseEntity<APIResponse<UserRegisterResponse>> addUser(UserRegistrationRequest request,
                                                                     Long roleId,
                                                                     HttpServletRequest httpServletRequest) throws URISyntaxException {
        userExist(request);
       list = new ArrayList<>();
        User user = new User(
                    idGenerator.generateId().toString(),
                    request.getFirstName(),
                    request.getLastName(),
                    request.getUsername(),
                    request.getEmailId(),
                    passwordEncoder.encode(request.getPassword()),
                    rolesService.getUserRolesById(roleId) // Assigning User Roles
                    );
        try {
            userRepository.save(user);
            list.add(mapper.userRegisterResponseMap(user));
        }
        catch (Exception e){
            throw new InternalServerException(ConstantMessage.USER_CREATION_ERROR);
        }
        apiResponse = new APIResponse(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED.toString(),
                "created",
                list,
                httpServletRequest);
        return ResponseEntity
                .created(new URI(apiResponse.getPath())).body(apiResponse);
    }


    @Override
    public ResponseEntity<APIResponse<UserRegisterResponse>> updateUserProfile(UserUpdateRequest request,
                                                                        UserPrincipal userPrincipal,
                                                                        HttpServletRequest httpServletRequest) throws URISyntaxException {
        list = new ArrayList<>();

        Optional<User> user = userRepository.findByUsername(userPrincipal.getUsername());

        if(!user.isPresent()){
            throw new IllegalStateException("User does not exist");
        }

        User entity = mapper.updateUserRequestMap(request);

        user.get().setUpdatedDate(entity.getUpdatedDate());

        if(!entity.getEmailId().isEmpty())
            user.get().setEmailId(entity.getEmailId());

        if(!entity.getFirstName().isEmpty())
            user.get().setFirstName(entity.getFirstName());

        if(!entity.getLastName().isEmpty())
            user.get().setLastName(entity.getLastName());

        try{
            userRepository.saveAndFlush(user.get());
            list.add(mapper.userRegisterResponseMap(user.get()));
        }

        catch (Exception e){
            throw new InternalServerException("User update exception");
        }

        apiResponse = new APIResponse(
                HttpStatus.OK.value(),
                HttpStatus.OK.toString(),
                "created",
                list,
                httpServletRequest);

        return ResponseEntity
                .status(apiResponse.getStatusCode()).body(apiResponse);
    }



    @Override
    public boolean userExistByUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean userExistByMobileNo(String mobileNo) {
        return userRepository.findByMobileNo(mobileNo).isPresent();
    }

    @Override
    public boolean userExistByEmailId(String emailId) {
        return userRepository.findByEmailId(emailId).isPresent();
    }

    @Override
    public void userExist(UserRegistrationRequest request) {
        if(userExistByUsername(request.getUsername())){
            LOGGER.info("User already exist by username");
            throw new UserExistException(ConstantMessage.USER_USERNAME_EXIST);
        }
        if(userExistByEmailId(request.getEmailId())){
            LOGGER.info("User already exist by email id");
            throw new UserExistException(ConstantMessage.USER_EMAIL_EXIST);
        }
    }

    @Override
    public void userNotExist(UserRegistrationRequest request) {
        if(!userExistByUsername(request.getUsername())){
            LOGGER.info("User already exist by username");
            throw new UserExistException(ConstantMessage.USER_USERNAME_EXIST);
        }
        if(!userExistByEmailId(request.getEmailId())){
            LOGGER.info("User already exist by email id");
            throw new UserExistException(ConstantMessage.USER_EMAIL_EXIST);
        }
    }

}
