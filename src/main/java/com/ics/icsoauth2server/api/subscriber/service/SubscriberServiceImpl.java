package com.ics.icsoauth2server.api.subscriber.service;


import com.ics.icsoauth2server.api.subscriber.SubscriberRequest;
import com.ics.icsoauth2server.api.subscriber.SubscriberResponse;
import com.ics.icsoauth2server.api.subscriber.exception.SubscribeAlreadyExistException;
import com.ics.icsoauth2server.api.subscriber.repository.SubscriberRepository;
import com.ics.icsoauth2server.domain.Subscriber;
import com.ics.icsoauth2server.exception.InternalServerException;
import com.ics.icsoauth2server.exception.PermissionDeniedException;
import com.ics.icsoauth2server.http.APIResponse;
import com.ics.icsoauth2server.oauth2.UserPrincipal;
import com.ics.icsoauth2server.utils.RolePermissionUtils;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.ics.icsoauth2server.helper.ConstantExceptionMessage.*;
import static com.ics.icsoauth2server.helper.ConstantMessage.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService{

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceImpl.class);

    private final SubscriberRepository subscriberRepository;
    private final IdGenerator idGenerator;
    private final ModelMapper modelMapper;

    private APIResponse<SubscriberResponse> apiResponse;
    private  List<SubscriberResponse> subscriberResponses;

    /**
     * Public API** Subscribing for new updates.
     * @param request
     * @param httpServletRequest
     * @return
     * @throws URISyntaxException
     */
    @Override
    public ResponseEntity<APIResponse<SubscriberResponse>> save(SubscriberRequest request,
                                                                HttpServletRequest httpServletRequest) throws URISyntaxException {

        Optional<Subscriber> optional = subscriberRepository.findByEmailId(request.getEmailId());

        if(optional.isPresent()){
            LOGGER.info("User already subscribed by email id ==> {}",request.getEmailId());
            throw new SubscribeAlreadyExistException("User already subscribed");
        }

        Subscriber subscriber = new Subscriber(
                idGenerator.generateId().toString(),
                request.getEmailId(),
                request.getFirstName(),
                request.getLastName(),
                request.getMessage()
        );

        try{
            subscriberRepository.save(subscriber);
        }

        catch (Exception ex){
            throw new InternalServerException(SUBSCRIBE_ERROR);
        }

        apiResponse = new APIResponse<>(
                CREATED.value(),
                CREATED.toString(),
                SUBSCRIBE_SUCCESS,
                createResponse(subscriber),
                httpServletRequest
        );

        return ResponseEntity
                .created(new URI(apiResponse.getPath())).body(apiResponse);
    }

    /**
     * Private API** To get all the subscribed User...
     * @param sortBy
     * @param sortOrder
     * @param currentPage
     * @param itemsPerPage
     * @param httpServletRequest
     * @param principal
     * @return
     */
    @Override
    public ResponseEntity<APIResponse<SubscriberResponse>> getAllSubscriber(String sortBy,
                                                                            String sortOrder,
                                                                            Integer currentPage,
                                                                            Integer itemsPerPage,
                                                                            HttpServletRequest httpServletRequest,
                                                                            UserPrincipal principal) {
        if(!RolePermissionUtils.isAdmin(principal)){
            LOGGER.info(PERMISSION_DENIED+" to the User ==> {}",principal.getUsername());
            throw new PermissionDeniedException(PERMISSION_DENIED);
        }

        Pageable paging = null;

        if(sortOrder.equalsIgnoreCase("Asc")) {
            LOGGER.info("Items");
            paging = PageRequest.of((currentPage - 1), itemsPerPage, Sort.by(sortBy).ascending());
        }

        else {
            paging = PageRequest.of((currentPage - 1), itemsPerPage, Sort.by(sortBy).descending());
        }

        try{

            Page<Subscriber> page = subscriberRepository.findAll(paging);

            if (!page.getContent().isEmpty()) {
                apiResponse = new APIResponse(
                        OK.value(),
                        OK.toString(),
                        POST_SUCCESS_FETCH,
                        mapToSubscriberList(page.getContent()),
                        page.getTotalPages(),
                        httpServletRequest
                );

                return ResponseEntity
                        .status(apiResponse.getStatusCode()).body(apiResponse);
            }
        }

        catch (Exception ex){
            throw new InternalServerException(SUBSCRIBER_FETCH_ERROR);
        }

        return emptyListResponse(httpServletRequest);
    }

    protected List<SubscriberResponse> createResponse(Subscriber subscriber){
        subscriberResponses = new ArrayList<>();
        subscriberResponses.add(modelMapper.map(subscriber,SubscriberResponse.class));
        return subscriberResponses;
    }

    protected ResponseEntity<APIResponse<SubscriberResponse>> emptyListResponse(HttpServletRequest httpServletRequest){
        apiResponse = new APIResponse(
                OK.value(),
                OK.toString(),
                POST_SUCCESS_FETCH,
                Collections.EMPTY_LIST,
                httpServletRequest
        );
        return ResponseEntity
                .status(apiResponse.getStatusCode()).body(apiResponse);
    }


    protected List<SubscriberResponse> mapToSubscriberList(List<Subscriber> subscribers){

        return subscribers
                .stream()
                .map(
                        subscriber ->
                                modelMapper.map(subscriber,SubscriberResponse.class)
                )
                .collect(Collectors.toList());
    }


}
