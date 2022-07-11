package com.mars.rover.api.impl.endpoint;

import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.mars.rover.api.RoverStateApiContract;
import com.mars.rover.api.impl.service.RoverStateService;
import com.mars.rover.api.response.RoverState;
import com.mars.rover.api.response.RoverStateResponse;
import com.mars.rover.api.response.RoverStateSuccessResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import static com.mars.rover.api.ApiContractConstants.JSON_MEDIA_TYPE;
import static com.mars.rover.api.ApiContractConstants.JSON_PROBLEM_MEDIA_TYPE;
import static com.mars.rover.api.RoverStateApiContract.ROVER_STATE_PATH;
import static com.mars.rover.api.impl.endpoint.RoverStateResponseHelper.*;

@RestController
@RequestMapping(
        value = ROVER_STATE_PATH,
        produces = {JSON_MEDIA_TYPE, JSON_PROBLEM_MEDIA_TYPE})
@AllArgsConstructor
public class RoverStateEndpoint implements RoverStateApiContract {

    private final RoverStateService roverStateService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public RoverStateResponse getRoverState() {
        var fetchedRoverState = roverStateService.getMostRecentRoverState();

        var status = createSuccessStatus();
        var metadata = createRequestMetadata(status);
        return RoverStateSuccessResponse.builder()
                .withMetadata(amendWithResponseParameters(metadata))
                .withState(fetchedRoverState)
                .build();
    }

    @PostMapping(consumes = APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public RoverStateResponse saveRoverState(@RequestBody RoverState roverState) {
        var roverStateResponse = roverStateService.updateRoverState(roverState);

        var status = createSuccessStatus(HttpStatus.ACCEPTED.getReasonPhrase());
        var metadata = createRequestMetadata(status);
        return RoverStateSuccessResponse.builder()
                .withMetadata(amendWithResponseParameters(metadata))
                .withState(roverStateResponse)
                .build();
    }
}