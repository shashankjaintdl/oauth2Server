package com.ics.icsoauth2server.oauth2.client;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationService;
import org.springframework.security.oauth2.provider.client.BaseClientDetails;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomClientRegistrationServiceImpl implements CustomClientRegistrationService{

    private final ClientRegistrationService clientRegistrationService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public ClientDetails save(ClientRegistrationRequest request) {
        ClientDetails clientDetails = create(request);
        clientRegistrationService.addClientDetails(clientDetails);
        return clientDetails;
    }

    @Override
    public List<ClientDetails> getAll() {
        return clientRegistrationService.listClientDetails();
    }

    protected ClientDetails create(ClientRegistrationRequest clientRegistrationRequest){
        BaseClientDetails clientDetails = new BaseClientDetails();
        clientDetails
                .setClientId(getClientId(clientRegistrationRequest));
        clientDetails
                .setClientSecret(getProtectedClientSecret(clientRegistrationRequest));
        if(clientRegistrationRequest.getClientMetadata()!=null) {
            clientDetails
                    .setRegisteredRedirectUri(getRedirectUris(clientRegistrationRequest));
            clientDetails
                    .setScope(clientRegistrationRequest.getClientMetadata().getScopes());
            clientDetails
                    .setAuthorities(clientRegistrationRequest.getClientMetadata().getAuthorities());
            clientDetails
                    .setResourceIds(clientRegistrationRequest.getClientMetadata().getResourceIds());
            clientDetails
                    .setAutoApproveScopes(clientRegistrationRequest.getClientMetadata().getAutoApproves());
            clientDetails
                    .setAuthorizedGrantTypes(clientRegistrationRequest.getClientMetadata().getGrantTypes());
            clientDetails
                    .setAdditionalInformation(clientRegistrationRequest.getClientMetadata().getAdditionalInformation());
            clientDetails
                    .setAccessTokenValiditySeconds(clientRegistrationRequest.getClientMetadata().getAccessTokenValiditySeconds());
            clientDetails
                    .setRefreshTokenValiditySeconds(clientRegistrationRequest.getClientMetadata().getRefreshTokenValiditySeconds());
        }
        return clientDetails;
    }

    protected String getClientId(ClientRegistrationRequest clientRegistrationRequest){
        return clientRegistrationRequest.getClientID().getValue();
    }

    protected Set<String> getRedirectUris(ClientRegistrationRequest clientRegistrationRequest){
        return clientRegistrationRequest.getClientMetadata().getRedirectURIs().stream().map(URI::toString).collect(Collectors.toSet( ));
    }

    protected String getProtectedClientSecret(ClientRegistrationRequest clientRegistrationRequest){
        return passwordEncoder
                .encode(clientRegistrationRequest.getSecret().getValue());
    }
}
