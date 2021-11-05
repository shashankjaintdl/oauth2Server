package com.ics.icsoauth2server.api.subscriber.service;

import com.ics.icsoauth2server.api.subscriber.SubscriberRequest;
import com.ics.icsoauth2server.api.subscriber.SubscriberResponse;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

public interface SubscriberService {

    ResponseEntity<APIResponse<SubscriberResponse>> save(SubscriberRequest request, HttpServletRequest httpServletRequest) throws URISyntaxException;

    ResponseEntity<APIResponse<SubscriberResponse>> getAllSubscriber(String sortBy, String sortOrder, Integer currentPage, Integer itemsPerPage, HttpServletRequest httpServletRequest, UserPrincipal principal);

}
