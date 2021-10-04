package com.ics.icsoauth2server.oauth2;



import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ics.icsoauth2server.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserPrincipal implements UserDetails {


    private static final Logger LOGGER = LoggerFactory.getLogger(UserPrincipal.class);

    private String  id;
    private String email;
    private String username;
    @JsonIgnore
    private String password;
    private Collection<? extends GrantedAuthority> authorities;
//    private  Map<String, Object> attributes;

    public UserPrincipal(String id,
                         String email,
                         String username,
                         String password,
                         Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = null;
        this.authorities = authorities;
    }

    public static UserPrincipal create(User user) {
        Set<GrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(r -> {
            authorities.add(new SimpleGrantedAuthority(r.getRole()));
            r.getPermissions().forEach(p -> {
                authorities.add(new SimpleGrantedAuthority(p.getPermission()));
            });
        });

        return new UserPrincipal(
                user.getUuid(),
                user.getEmailId(),
                user.getUsername(),
                user.getPassword(),
                authorities
        );
    }

    public static UserPrincipal create(User user,
                                       Map<String, Object> attributes) {
        UserPrincipal userPrincipal = UserPrincipal.create(user);
//        userPrincipal.setAttributes(attributes);
        return userPrincipal;
    }

    public static void check(User user){
        if(!user.isEnabled()) {
            LOGGER.debug("Failed to authenticate since user account is disabled");
            throw new DisabledException("User account is disabled");
        }
        if(!user.isAccountNonLocked()){
            LOGGER.debug("Failed to authenticate since user account is locked");
            throw new LockedException("User account is locked");
        }
        if(!user.isCredentialsNonExpired()){
            LOGGER.debug("Failed to authenticate since user account credentials have expired");
            throw new CredentialsExpiredException("User account credentials has been expired");
        }
        if(!user.isAccountNonExpired()){
            LOGGER.debug("Failed to authenticate since user account is expired");
            throw new AccountExpiredException("User account is expired");
        }
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


}

