package com.ics.icsoauth2server.oauth2.provider;

import com.ics.icsoauth2server.oauth2.UserPrincipal;
import com.ics.icsoauth2server.security.service.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UsernamePasswordAuthenticationTokenProvider implements AuthenticationProvider {

    private final static Logger LOGGER = LoggerFactory.getLogger(UsernamePasswordAuthenticationTokenProvider.class);

    @Autowired
    private  UserDetailsServiceImpl userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        final String username = (authentication.getPrincipal() == null) ? "NONE_PROVIDED" : authentication.getName();
        if(LOGGER.isDebugEnabled()){
            LOGGER.debug("Authenticating the username "+username);
        }
        UserPrincipal userPrincipal = null;
        try {
           userPrincipal = userDetailsService.loadUserByUsername(username);
        }
        catch (UsernameNotFoundException exception){
            throw new BadCredentialsException("Username does not exist");
        }
        return createSuccessfulAuthentication(authentication,userPrincipal);
    }

    private Authentication createSuccessfulAuthentication(final Authentication authentication, final UserPrincipal user) {
        UsernamePasswordAuthenticationToken token =
                new UsernamePasswordAuthenticationToken(user ,authentication.getCredentials(), user.getAuthorities());
        token.setDetails(authentication.getDetails());
        return token;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        System.out.print(authentication);
        return true;
    }

}
