package com.ics.icsoauth2server.api.subscriber.service;


import com.ics.icsoauth2server.api.subscriber.SubscriberRequest;
import com.ics.icsoauth2server.api.subscriber.SubscriberResponse;
import com.ics.icsoauth2server.api.subscriber.mapper.SubscriberMapper;
import com.ics.icsoauth2server.api.subscriber.repository.SubscriberRepository;
import com.ics.icsoauth2server.domain.Subscriber;
import com.ics.icsoauth2server.exception.InternalServerException;
import com.ics.icsoauth2server.http.APIResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.IdGenerator;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static com.ics.icsoauth2server.helper.ConstantMessage.*;
import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class SubscriberServiceImpl implements SubscriberService{

    private final static Logger LOGGER = LoggerFactory.getLogger(SubscriberServiceImpl.class);

    private final SubscriberRepository subscriberRepository;
    private final IdGenerator idGenerator;

    private APIResponse<SubscriberResponse> apiResponse;
    private  List<SubscriberResponse> subscriberResponses;
    private SubscriberMapper mapper = new SubscriberMapper();

    /**
     *
     * @param request
     * @param httpServletRequest
     * @return
     * @throws URISyntaxException
     */
    @Override
    public ResponseEntity<APIResponse<SubscriberResponse>> save(SubscriberRequest request,
                                                                HttpServletRequest httpServletRequest) throws URISyntaxException {
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


    protected List<SubscriberResponse> createResponse(Subscriber subscriber){
        subscriberResponses = new ArrayList<>();
        subscriberResponses.add(mapper.mapResponse(subscriber));
        return subscriberResponses;
    }


}
