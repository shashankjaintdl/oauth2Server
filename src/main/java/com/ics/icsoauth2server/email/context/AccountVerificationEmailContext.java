package com.ics.icsoauth2server.email.context;

import com.ics.icsoauth2server.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;

public class AccountVerificationEmailContext extends AbstractEmailContext{

    private String token;

    @Value("${api.version}")
    private String apiVersion;


    @Override
    public <T> void init(T context) {
        User userAccount= (User) context;
        put("username",userAccount.getUsername());
        setTemplateLocation("verify-email.ftl");
        setSubject("verify your Account");
        setFrom("jainshashank562@gmail.com");
        setTo("shashankjain536@gmail.com");
    }

    public void setToken(String token) {
        this.token = token;
        put("token", token);
    }

    public void buildVerificationUrl(final String baseURL, final String token){
        final String url= UriComponentsBuilder.fromHttpUrl(baseURL)
                .path("/user-account/verify").queryParam("token", token).toUriString();
        put("verificationURL", url);
    }

}
