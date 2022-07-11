package com.mars.rover.api.impl.endpoint;

import java.text.MessageFormat;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.mars.rover.api.impl.model.exception.InvalidIdRoverStateProblem;
import com.mars.rover.api.impl.service.RoverStateService;
import com.mars.rover.api.response.RoverState;
import com.mars.rover.api.response.RoverStateSuccessResponse;
import com.mars.rover.core.Direction;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.*;

import static com.mars.rover.api.impl.MarsRoverApplicationConstants.INVALID_ROVER_STATE_MESSAGE;

@ExtendWith(SpringExtension.class)
class RoverStateEndpointTest {

    @MockBean
    private RoverStateService roverStateService;

    private RoverStateEndpoint roverStateEndpoint;

    private RoverState roverState1;
    private RoverState roverState2;

    @BeforeEach
    void setUp() {
        roverState1 = RoverState.builder()
                .withDirection(Direction.EST)
                .withLocation(
                        RoverState.Location.builder()
                                .withXCoordinate(-1)
                                .withYCoordinate(1)
                                .build())
                .build();

        roverState2 = RoverState.builder()
                .withDirection(Direction.SOUTH)
                .withLocation(
                        RoverState.Location.builder()
                                .withXCoordinate(0)
                                .withYCoordinate(0)
                                .build())
                .build();

        roverStateService = mock(RoverStateService.class);

        roverStateEndpoint = new RoverStateEndpoint(roverStateService);
    }

    @Test
    void should_fetch_rover_state() {
        when(roverStateService.getMostRecentRoverState())
                .thenReturn(roverState2);

        var fetchedRoverStateResponse =
                (RoverStateSuccessResponse) roverStateEndpoint.getRoverState();

        assertThat(fetchedRoverStateResponse.state()).isEqualTo(roverState2);
    }

    @Test
    void should_alter_the_rover_location() {
        var newXCoordinate = 0;
        var newYCoordinate = 1;

        roverState1 = roverState1.toBuilder()
                .withLocation(
                        RoverState.Location.builder()
                                .withXCoordinate(newXCoordinate)
                                .withYCoordinate(newYCoordinate)
                                .build())
                .build();
        when(roverStateService.updateRoverState(roverState1))
                .thenReturn(roverState1);

        var savedRoverState =
                ((RoverStateSuccessResponse)
                        roverStateEndpoint.saveRoverState(roverState1)).state();

        assertThat(savedRoverState).isNotNull();
        assertThat(savedRoverState.direction()).isNotNull().isEqualTo(roverState1.direction());
        assertThat(savedRoverState.location()).isNotNull();
        assertThat(savedRoverState.location().xCoordinate()).isEqualTo(newXCoordinate);
        assertThat(savedRoverState.location().yCoordinate()).isEqualTo(newYCoordinate);
    }

    @Test
    void should_deny_the_rover_state_update() {
        doThrow(new InvalidIdRoverStateProblem(INVALID_ROVER_STATE_MESSAGE, null))
                .when(roverStateService).updateRoverState((isNull()));

        var thrown = assertThrows(
                InvalidIdRoverStateProblem.class,
                () -> roverStateEndpoint.saveRoverState(null),
                "An" + InvalidIdRoverStateProblem.class.getSimpleName() + " was expected");

        assertThat(thrown.getMessage())
                .isEqualTo(MessageFormat.format(INVALID_ROVER_STATE_MESSAGE + ": {0}", "null"));
    }
}