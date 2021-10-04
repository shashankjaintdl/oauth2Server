package com.ics.icsoauth2server.oauth2.client;

import com.ics.icsoauth2server.oauth2.id.ClientID;
import com.ics.icsoauth2server.oauth2.id.Secret;
import net.jcip.annotations.Immutable;



@Immutable
public class ClientRegistrationRequest {

    private  ClientID clientID;
    private  Secret secret;
    private  ClientMetadata clientMetadata;


    public ClientRegistrationRequest(ClientID clientId, Secret secret){
        this.clientID = clientId;
        this.secret = secret;
    }

    public ClientRegistrationRequest(ClientID clientId, Secret secret, ClientMetadata clientMetadata){
        this(clientId,secret);
        if(clientMetadata==null){
            throw new IllegalArgumentException("Client metadata must not be null");
        }
        this.clientMetadata = clientMetadata;
    }

    public ClientRegistrationRequest(){}

    public ClientID getClientID() {
        return clientID;
    }

    public void setClientID(ClientID clientID) {
        this.clientID = clientID;
    }

    public Secret getSecret() {
        return secret;
    }

    public void setSecret(Secret secret) {
        this.secret = secret;
    }

    public ClientMetadata getClientMetadata() {
        return clientMetadata;
    }

    public void setClientMetadata(ClientMetadata clientMetadata) {
        this.clientMetadata = clientMetadata;
    }


}
