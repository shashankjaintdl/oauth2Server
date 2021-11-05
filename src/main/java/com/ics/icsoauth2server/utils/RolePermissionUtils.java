package com.ics.icsoauth2server.utils;

import com.ics.icsoauth2server.oauth2.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.ics.icsoauth2server.security.config.Roles.*;

public final class RolePermissionUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(RolePermissionUtils.class);

    public RolePermissionUtils(){}

    public static Boolean isAdmin(UserPrincipal principal){
        LOGGER.info("Checking if User Principal is Admin ==> {]",principal.getUsername());
        return principal
                .getAuthorities()
                .stream().
                anyMatch(
                        (x)->x.getAuthority()
                                .contains(ROLE_AD.name())
                );
    }


    public static Boolean isUser(UserPrincipal principal){
        LOGGER.info("Checking if User Principal is User> {]",principal.getUsername());
        return principal
                .getAuthorities()
                .stream().
                anyMatch(
                        (x)->x.getAuthority()
                                .contains(ROLE_USER.name())
                );
    }

    public static Boolean hasBlogWritePermission(UserPrincipal principal){
        LOGGER.info("Checking if User Principal has Blog write permission ==> {]",principal.getUsername());
        return principal
                .getAuthorities()
                .stream().
                anyMatch(
                        (x)->x.getAuthority()
                                .contains(BLOG_WRITE.name())
                );
    }

    public static Boolean hasBlogDeletePermission(UserPrincipal principal){
        LOGGER.info("Checking if User Principal has Blog delete permission ==> {]",principal.getUsername());
        return principal
                .getAuthorities()
                .stream().
                anyMatch(
                        (x)->x.getAuthority()
                                .contains(BLOG_DELETE.name())
                );
    }
    public static Boolean hasReadPermission(){
        return false;
    }
}
