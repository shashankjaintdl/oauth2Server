package com.ics.icsoauth2server.oauth2.token;

import com.ics.icsoauth2server.api.user.repository.UserRepository;
import com.ics.icsoauth2server.domain.User;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import javax.transaction.Transactional;
import java.util.*;

@Transactional
public class CustomTokenEnhancer extends JwtAccessTokenConverter {

    private final static Logger LOGGER = LoggerFactory.getLogger(CustomTokenEnhancer.class);

    @Autowired
    private  UserRepository userRepository;

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
            List<String> permissions = new ArrayList<>();
            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();

            LOGGER.info("Creating the custom token for user [ {} ]",userPrincipal.getUsername());

            User user = userRepository
                        .findByUsername(userPrincipal.getUsername()).orElseThrow(
                            ()->new UsernameNotFoundException("")
                        );
            final Map<String, Object> additionalInfo = new HashMap<>();

            additionalInfo.put("id",user.getId());

//            additionalInfo.put("name",String.format(user.getFirstName()+" "+user.getLastName()));

            additionalInfo.put("email",user.getEmailId());

//            user.getRoles().forEach(r->r.getPermissions().forEach(p->permissions.add(p.getPermission())));
//
//            additionalInfo.put("permissions",permissions);

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInfo);

            accessToken = super.enhance(accessToken, authentication);

            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(new HashMap<>());

        return accessToken;
    }


}
