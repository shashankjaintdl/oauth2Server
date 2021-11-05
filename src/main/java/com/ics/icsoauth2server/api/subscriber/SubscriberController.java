package com.ics.icsoauth2server.api.subscriber;

import com.ics.icsoauth2server.api.subscriber.service.SubscriberService;
import com.ics.icsoauth2server.http.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.net.URISyntaxException;

import static com.ics.icsoauth2server.helper.ConstantMessage.API_VERSION;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(
        value = SubscriberController.ENDPOINT,
        consumes = {APPLICATION_JSON_VALUE},
        produces = {APPLICATION_JSON_VALUE}
)
@RequiredArgsConstructor
public class SubscriberController {

    protected final static String ENDPOINT = API_VERSION+"/subscribe";

    private final SubscriberService subscriberService;

    @PostMapping("/create")
    public ResponseEntity<APIResponse<SubscriberResponse>> create(@RequestBody @Valid SubscriberRequest request,
                                                                  HttpServletRequest httpServletRequest) throws URISyntaxException {
        return subscriberService.save(request,httpServletRequest);
    }




}
