package com.ics.icsoauth2server.security;

import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class CustomAccessDecisionVoter implements AccessDecisionVoter<Object> {

    private String anonymousUserName ="anonymous";
    @Override
    public boolean supports(ConfigAttribute attribute) {
        System.out.println(attribute);
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    @Override
    public int vote(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
        final String username = (authentication.getPrincipal() == null ) ? "NONE_PROVIDED" : authentication.getName();
        if(username.equalsIgnoreCase(anonymousUserName)){
            return ACCESS_DENIED;
        }
        return ACCESS_GRANTED;
    }
}
