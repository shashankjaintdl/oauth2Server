package com.ics.icsoauth2server.oauth2;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ics.icsoauth2server.api.user.repository.UserRepository;
import com.ics.icsoauth2server.domain.User;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerEndpointsConfiguration;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

@Transactional
@NoArgsConstructor
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomTokenEnhancer.class);

    @Autowired
    private UserRepository userRepository;



        @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {

        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

        LOGGER.info("Creating the custom token for user [ {} ]",userPrincipal.getUsername());

        User user = userRepository
                    .findByUsername(userPrincipal.getUsername()).orElseThrow(
                        ()->new UsernameNotFoundException("")
                    );

        final Map<String, Object> additionalInfo = new HashMap<>();

        additionalInfo.put("id",user.getUuid());

        additionalInfo.put("Name",String.format(user.getFirstName()+" "+user.getLastName()));

        additionalInfo.put("Email",user.getEmailId());

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

        accessToken = super.enhance(accessToken, authentication);

        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());

        return accessToken;
    }


}
