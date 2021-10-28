package com.ics.icsoauth2server.security.config;

public enum Roles {
    ROLE_AD(1L),
    ROLE_USER(2L),
    ROLE_MGR(3L);

    Long val;
    Roles(Long i) {
        val =  i;
    }

    public Long getValue(){
        return val;
    }

}
