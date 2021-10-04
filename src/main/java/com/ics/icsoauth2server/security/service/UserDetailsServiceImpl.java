package com.ics.icsoauth2server.security.service;

import com.ics.icsoauth2server.api.user.repository.UserRepository;
import com.ics.icsoauth2server.domain.User;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.log.LogMessage;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    private final UserRepository userRepository;

    @Override
    public UserPrincipal loadUserByUsername(String username) throws UsernameNotFoundException {

        if(LOGGER.isDebugEnabled()){
            LOGGER.debug(String.valueOf(LogMessage.of(()->"[Searching "+username+" username from database]")));
        }

        User user = userRepository
                .findByUsername(username)
                .orElseThrow(
                        ()->new UsernameNotFoundException("Username does not exist")
                );

        UserPrincipal.check(user);

        return UserPrincipal.create(user);
    }

}
