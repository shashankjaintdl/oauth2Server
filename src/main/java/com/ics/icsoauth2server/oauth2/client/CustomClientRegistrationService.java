package com.ics.icsoauth2server.oauth2.client;


import org.springframework.security.oauth2.provider.ClientDetails;

import java.util.List;

public interface CustomClientRegistrationService {
    ClientDetails save(ClientRegistrationRequest request);
    List<ClientDetails> getAll();
}
