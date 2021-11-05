package com.ics.icsoauth2server.api.subscriber.service;

import com.ics.icsoauth2server.api.subscriber.SubscriberRequest;
import com.ics.icsoauth2server.api.subscriber.SubscriberResponse;
import com.ics.icsoauth2server.http.APIResponse;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

public interface SubscriberService {

    ResponseEntity<APIResponse<SubscriberResponse>> save(SubscriberRequest request, HttpServletRequest httpServletRequest) throws URISyntaxException;

}
