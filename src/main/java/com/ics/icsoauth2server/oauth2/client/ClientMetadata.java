package com.ics.icsoauth2server.oauth2.client;

import org.springframework.security.core.GrantedAuthority;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class ClientMetadata {

    //Required
    private Set<String> resourceIds;

    //Required
    private Set<String> scopes;

    //Required
    private Set<String> grantTypes;

    //Optional
    private Set<URI> redirectURIs;

    //Optional
    private Set<String> autoApproves;

    //Required
    private List<GrantedAuthority> authorities;

    //Required
    private Integer accessTokenValiditySeconds = 3600;

    //Required
    private Integer refreshTokenValiditySeconds = 84000;

    //Optional
    private Map<String, Object> additionalInformation = new LinkedHashMap<String, Object>();


    public ClientMetadata(Set<String> resourceIds,Set<String> scopes,Set<String> grantTypes,
                          List<GrantedAuthority> authorities, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds){
        if(resourceIds.isEmpty()){
            throw new IllegalArgumentException("Resource Id must not be empty");
        }
        if (scopes.isEmpty()){
            throw new IllegalArgumentException("Scopes must not be empty");
        }
        if (authorities.isEmpty()){
            throw new IllegalArgumentException("Authorities must not be empty");
        }
        this.resourceIds = resourceIds;
        this.scopes = scopes;
        this.grantTypes = grantTypes;
        this.authorities = authorities;
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public ClientMetadata(Set<String> resourceIds,Set<String> scopes,Set<String> grantTypes,Set<URI> redirectURIs,Set<String> autoApproves,
                          List<GrantedAuthority> authorities, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds){
        this(resourceIds,scopes,grantTypes,authorities,accessTokenValiditySeconds,refreshTokenValiditySeconds);
        this.redirectURIs = redirectURIs;
        this.autoApproves = autoApproves;
    }

    public ClientMetadata(Set<String> resourceIds,Set<String> scopes,Set<String> grantTypes,Set<URI> redirectURIs,Set<String> autoApproves,
                          List<GrantedAuthority> authorities, Integer accessTokenValiditySeconds, Integer refreshTokenValiditySeconds,Map<String,Object> additionalInformation){
        this(resourceIds,scopes,grantTypes,redirectURIs,autoApproves,authorities,accessTokenValiditySeconds,refreshTokenValiditySeconds);
        this.additionalInformation  = additionalInformation;
    }

    public ClientMetadata(){
    }

    public Set<String> getResourceIds() {
        return resourceIds;
    }

    public void setResourceIds(Set<String> resourceIds) {
        this.resourceIds = resourceIds;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }

    public Set<String> getGrantTypes() {
        return grantTypes;
    }

    public void setGrantTypes(Set<String> grantTypes) {
        this.grantTypes = grantTypes;
    }

    public Set<URI> getRedirectURIs() {
        return redirectURIs;
    }

    public void setRedirectURIs(Set<URI> redirectURIs) {
        this.redirectURIs = redirectURIs;
    }

    public Set<String> getAutoApproves() {
        return autoApproves;
    }

    public void setAutoApproves(Set<String> autoApproves) {
        this.autoApproves = autoApproves;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Integer getAccessTokenValiditySeconds() {
        return accessTokenValiditySeconds;
    }

    public void setAccessTokenValiditySeconds(Integer accessTokenValiditySeconds) {
        this.accessTokenValiditySeconds = accessTokenValiditySeconds;
    }

    public Integer getRefreshTokenValiditySeconds() {
        return refreshTokenValiditySeconds;
    }

    public void setRefreshTokenValiditySeconds(Integer refreshTokenValiditySeconds) {
        this.refreshTokenValiditySeconds = refreshTokenValiditySeconds;
    }

    public Map<String, Object> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(Map<String, Object> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
