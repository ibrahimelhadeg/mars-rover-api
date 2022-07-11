package com.mars.rover.api.impl.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.mars.rover.api.impl.service.RoverStateService;
import com.mars.rover.api.response.Metadata;
import com.mars.rover.api.response.RoverState;
import com.mars.rover.api.response.RoverStateSuccessResponse;
import com.mars.rover.api.response.Status;
import com.mars.rover.core.Direction;
import io.restassured.path.json.JsonPath;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import static com.mars.rover.api.RoverStateApiContract.ROVER_STATE_PATH;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = RoverStateEndpoint.class)
class RoverStateEndpointRestTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String PROPERTY_PATH_SEPARATOR = ".";

    private static final String STATUS_CODE_JSON_PATH =
            RoverStateSuccessResponse.Fields.METADATA + PROPERTY_PATH_SEPARATOR
                    + Metadata.Fields.STATUS + PROPERTY_PATH_SEPARATOR
                    + Status.Fields.STATUS_CODE;
    private static final String ROVER_STATE_DIRECTION_JSON_PATH =
            RoverStateSuccessResponse.Fields.STATE + PROPERTY_PATH_SEPARATOR + RoverState.Fields.DIRECTION;
    private static final String ROVER_STATE_X_COORDINATE_JSON_PATH =
            RoverStateSuccessResponse.Fields.STATE + PROPERTY_PATH_SEPARATOR
                    + RoverState.Fields.LOCATION + PROPERTY_PATH_SEPARATOR
                    + RoverState.Location.Fields.X_COORDINATE;

    private static final String ROVER_STATE_Y_COORDINATE_JSON_PATH =
            RoverStateSuccessResponse.Fields.STATE + PROPERTY_PATH_SEPARATOR
                    + RoverState.Fields.LOCATION + PROPERTY_PATH_SEPARATOR
                    + RoverState.Location.Fields.Y_COORDINATE;

    private static final String ROVER_STATE_DIRECTION = "EST";
    private static final int ROVER_STATE_X_COORDINATE = -1;
    private static final int ROVER_STATE_X_COORDINATE_UPDATED = 0;
    private static final int ROVER_STATE_Y_COORDINATE = 1;

    @MockBean
    private RoverStateService roverStateService;
    @MockBean
    private RoverStateResponseHelper roverStateResponseHelper;

    private String roverStateUpdate;

    private RoverState roverState;
    private RoverState updatedRoverState;

    @BeforeEach
    void setUp() {
        roverState = RoverState.builder()
                .withDirection(Direction.valueOf(ROVER_STATE_DIRECTION))
                .withLocation(
                        RoverState.Location.builder()
                                .withXCoordinate(ROVER_STATE_X_COORDINATE)
                                .withYCoordinate(ROVER_STATE_Y_COORDINATE)
                                .build())
                .build();

        roverStateUpdate = "{ \"direction\": \"" + ROVER_STATE_DIRECTION + "\", " +
                "\"location\" : { \"xCoordinate\": " + ROVER_STATE_X_COORDINATE_UPDATED + ", " +
                "\"yCoordinate\": " + ROVER_STATE_Y_COORDINATE + " } }";

        updatedRoverState = RoverState.builder()
                .withDirection(Direction.valueOf(ROVER_STATE_DIRECTION))
                .withLocation(
                        RoverState.Location.builder()
                                .withXCoordinate(ROVER_STATE_X_COORDINATE_UPDATED)
                                .withYCoordinate(ROVER_STATE_Y_COORDINATE)
                                .build())
                .build();
    }

    @Test
    /*
    An attempt to fetch the rover's state should result in an HTTP 200 OK with response, similar to:
        {
            "metadata": {
                "serviceName": "RoverState Service",
                "serviceVersion": "1.0",
                "status": {
                    "success": true,
                    "statusCode": "OK"
                },
                "requestId": "f24f2cf9-d044-4c0c-9f79-f40707c129ac",
                "requestTimestamp": "1499397004493",
                "responseId": "6a023dbd-8990-44fe-b2bd-93a49c3fceaf",
                "responseTimestamp": "1499397004493"
            },
            "state": {
                "direction": "EST",
                "location": {
                    "xCoordinate": -1,
                    "yCoordinate": 1
                }
            }
        }
    */
    void should_handle_rover_state_retrieval_requests() throws Exception {

        when(roverStateService.getMostRecentRoverState())
                .thenReturn(roverState);

        // Invoke the GET operation at the /roverStates endpoint
        var requestBuilder =
                MockMvcRequestBuilders
                        .get(ROVER_STATE_PATH)
                        .accept(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder)
                .andReturn();

        var response = result.getResponse();
        var jsonString = response.getContentAsString();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.OK.value());

        assertThat(JsonPath.with(jsonString).getString(STATUS_CODE_JSON_PATH))
                .isEqualTo(HttpStatus.OK.getReasonPhrase());

        assertThat(JsonPath.with(jsonString).getString(ROVER_STATE_DIRECTION_JSON_PATH))
                .isEqualTo(ROVER_STATE_DIRECTION);

        assertThat(JsonPath.with(jsonString).getInt(ROVER_STATE_X_COORDINATE_JSON_PATH))
                .isEqualTo(ROVER_STATE_X_COORDINATE);

        assertThat(JsonPath.with(jsonString).getInt(ROVER_STATE_Y_COORDINATE_JSON_PATH))
                .isEqualTo(ROVER_STATE_Y_COORDINATE);
    }

    @Test
    void should_handle_rover_state_update_requests() throws Exception {

        when(roverStateService.updateRoverState(any(RoverState.class)))
                .thenReturn(updatedRoverState);

        // Invoke the POST operation at the /roverStates endpoint and
        // supply a payload JSON of roverState1String.
        var requestBuilder =
                MockMvcRequestBuilders
                        .post(ROVER_STATE_PATH)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(roverStateUpdate)
                        .contentType(MediaType.APPLICATION_JSON);

        var result = mockMvc.perform(requestBuilder)
                .andReturn();

        var response = result.getResponse();
        var jsonString = response.getContentAsString();

        assertThat(response.getStatus())
                .isEqualTo(HttpStatus.ACCEPTED.value());

        assertThat(JsonPath.with(jsonString).getString(STATUS_CODE_JSON_PATH))
                .isEqualTo(HttpStatus.ACCEPTED.getReasonPhrase());

        assertThat(JsonPath.with(jsonString).getString(ROVER_STATE_DIRECTION_JSON_PATH))
                .isEqualTo(ROVER_STATE_DIRECTION);

        assertThat(JsonPath.with(jsonString).getInt(ROVER_STATE_X_COORDINATE_JSON_PATH))
                .isEqualTo(ROVER_STATE_X_COORDINATE_UPDATED);

        assertThat(JsonPath.with(jsonString).getInt(ROVER_STATE_Y_COORDINATE_JSON_PATH))
                .isEqualTo(ROVER_STATE_Y_COORDINATE);
    }
}