package com.ics.icsoauth2server.api.subscriber;

import com.ics.icsoauth2server.api.subscriber.service.SubscriberService;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/all")
    public ResponseEntity<APIResponse<SubscriberResponse>> getAll(@RequestParam(name = "sortBy",defaultValue = "",required = false) String sortBy,
                                                                  @RequestParam(name = "sortOrder", defaultValue = "",required = false) String sortOrder,
                                                                  @RequestParam(name = "currentPage",defaultValue = "1",required = false) Integer currentPage,
                                                                  @RequestParam(name = "itemsPerPage",defaultValue = "1",required = false) Integer itemsPerPage,
                                                                  HttpServletRequest httpServletRequest,
                                                                  UserPrincipal principal){
        return subscriberService.getAllSubscriber(sortBy,sortOrder,currentPage,itemsPerPage,httpServletRequest,principal);
    }



}
