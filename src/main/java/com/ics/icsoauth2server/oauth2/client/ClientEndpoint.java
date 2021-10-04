package com.ics.icsoauth2server.oauth2.client;

import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.GrantType;
import com.ics.icsoauth2server.oauth2.id.ClientID;
import com.ics.icsoauth2server.oauth2.id.Secret;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(ClientEndpoint.ENDPOINT)
@RequiredArgsConstructor
public class ClientEndpoint {

    protected final static String ENDPOINT = "/client";

    private final CustomClientRegistrationService clientRegistrationService;
    private APIResponse apiResponse;

    @PostMapping("/create")
    public ResponseEntity<ClientDetails> addNewClient(@RequestBody ClientRegistrationRequest clientRegistrationRequest){
        return ResponseEntity
                .ok()
                .body(clientRegistrationService.save(clientRegistrationRequest));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getClient(){
        ClientID clientID = new ClientID(64);
        System.out.println(clientID.getValue());
        Secret secret = new Secret();
        System.out.println(secret.getValue());
        Set<String> grantTypes = new HashSet<>();
        grantTypes.add(GrantType.PASSWORD.getGrantType());
        grantTypes.add(GrantType.IMPLICIT.getGrantType());
       // ClientMetadata metadata = new ClientMetadata(null,null,grantTypes,null,null,null);
        ClientRegistrationRequest clientRegistrationRequest = new ClientRegistrationRequest(clientID,secret);
        return ResponseEntity.ok().body(clientRegistrationService.save(clientRegistrationRequest));
    }
}
