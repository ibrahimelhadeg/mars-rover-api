package com.mars.rover.api.impl.service;

import java.time.Instant;
import java.util.Optional;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.mars.rover.api.impl.dao.RoverStateRepository;
import com.mars.rover.api.impl.model.RoverStateEntity;
import com.mars.rover.api.response.RoverState;
import com.mars.rover.core.Direction;
import com.mars.rover.core.Location;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RoverStateEntityServiceTest {

    private static final Instant FIXED_INSTANT = Instant.ofEpochMilli(1656633600);
    private static final Direction DIRECTION = Direction.SOUTH;
    private static final int LOCATION_COORDINATE = -2;

    @MockBean
    private RoverStateRepository roverStateRepository;

    private RoverStateServiceImpl roverStateService;

    private RoverState roverState;

    @BeforeEach
    void prepare() {
        var roverStateEntity = RoverStateEntity.builder()
                .withUpdateTimestamp(FIXED_INSTANT)
                .withDirection(DIRECTION)
                .withLocation(
                        Location.builder()
                                .withX(LOCATION_COORDINATE)
                                .withY(LOCATION_COORDINATE)
                                .build())
                .build();
        roverState = RoverState.builder()
                .withDirection(DIRECTION)
                .withLocation(
                        RoverState.Location.builder()
                                .withXCoordinate(LOCATION_COORDINATE)
                                .withYCoordinate(LOCATION_COORDINATE)
                                .build())
                .build();

        when(roverStateRepository.findTopByOrderByUpdateTimestampDesc())
                .thenReturn(Optional.of(roverStateEntity));
        when(roverStateRepository.save(any(RoverStateEntity.class)))
                .thenAnswer(invocation -> invocation.getArguments()[0]);

        roverStateService = new RoverStateServiceImpl(roverStateRepository);
    }

    @Test
    void should_map_retrieved_entity_properly() {
        var retrievedRoverState = roverStateService.getMostRecentRoverState();

        assertThat(retrievedRoverState).isEqualTo(roverState);
    }

    @Test
    void should_map_new_state_correctly_to_entity_while_adding_an_update_timestamp() {
        var newRoverState = roverStateService.updateRoverState(roverState);

        assertThat(newRoverState).isEqualTo(roverState);
    }
}