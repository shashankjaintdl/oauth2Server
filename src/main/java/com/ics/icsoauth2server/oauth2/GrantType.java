package com.ics.icsoauth2server.oauth2;

public enum GrantType {

    AUTHORIZATION_CODE("authorization_code"),
    CLIENT_CREDENTIAL("client_credential"),
    IMPLICIT("implicit"),
    PASSWORD("password");

    private final String grantType;

    GrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getGrantType() {
        return grantType;
    }
}
