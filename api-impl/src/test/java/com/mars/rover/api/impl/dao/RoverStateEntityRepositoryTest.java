package com.mars.rover.api.impl.dao;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.mars.rover.api.impl.MarsRoverApplication;
import com.mars.rover.api.impl.model.RoverStateEntity;
import com.mars.rover.core.Direction;
import com.mars.rover.core.Location;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = MarsRoverApplication.class)
@TestExecutionListeners({
        DependencyInjectionTestExecutionListener.class,
        DirtiesContextTestExecutionListener.class,
        TransactionalTestExecutionListener.class})
@DirtiesContext
class RoverStateEntityRepositoryTest {

    @Autowired
    private RoverStateRepository repository;

    @BeforeEach
    void prepare_in_memory_database() {
        repository.save(
                RoverStateEntity.builder()
                        .withUpdateTimestamp(
                                Instant.parse("2022-07-02T10:15:30.00Z"))
                        .withDirection(Direction.EST)
                        .withLocation(
                                Location.builder()
                                        .withX(0)
                                        .withY(0)
                                        .build())
                        .build());
        repository.save(
                RoverStateEntity.builder()
                        .withUpdateTimestamp(
                                Instant.parse("2022-07-02T10:15:35.00Z"))
                        .withDirection(Direction.EST)
                        .withLocation(
                                Location.builder()
                                        .withX(1)
                                        .withY(0)
                                        .build())
                        .build());
        repository.save(
                RoverStateEntity.builder()
                        .withUpdateTimestamp(
                                Instant.parse("2022-07-02T10:15:40.00Z"))
                        .withDirection(Direction.NORTH)
                        .withLocation(
                                Location.builder()
                                        .withX(1)
                                        .withY(0)
                                        .build())
                        .build());
    }

    @AfterEach
    void clear_in_memory_database() {
        repository.deleteAll();
    }

    @Test
    void should_retrieve_most_recent_state() {
        var mostRecentRoverState =
                repository.findTopByOrderByUpdateTimestampDesc().orElse(null);
        assertThat(mostRecentRoverState).isNotNull();

        assertThat(mostRecentRoverState.updateTimestamp())
                .isNotNull()
                .isEqualTo(Instant.parse("2022-07-02T10:15:40.00Z"));
        assertThat(mostRecentRoverState.direction())
                .isNotNull()
                .isEqualTo(Direction.NORTH);
        assertThat(mostRecentRoverState.location())
                .isNotNull();
        assertThat(mostRecentRoverState.location().x())
                .isEqualTo(1);
        assertThat(mostRecentRoverState.location().y())
                .isZero();
    }

    @Test
    void should_add_a_new_rover_state() {
        var now = Instant.now();
        var direction = Direction.NORTH;
        var xCoordinate = 0;
        var yCoordinate = 0;

        var roverState = RoverStateEntity.builder()
                .withUpdateTimestamp(now)
                .withDirection(direction)
                .withLocation(
                        Location.builder()
                                .withX(xCoordinate)
                                .withY(yCoordinate)
                                .build())
                .build();

        var newRoverState = repository.save(roverState);

        // last element of the dataset is the most recent one
        assertThat(newRoverState.updateTimestamp())
                .isNotNull()
                .isEqualTo(now);
        assertThat(newRoverState.direction())
                .isNotNull()
                .isEqualTo(direction);
        assertThat(newRoverState.location())
                .isNotNull();
        assertThat(newRoverState.location().x())
                .isEqualTo(xCoordinate);
        assertThat(newRoverState.location().y())
                .isEqualTo(yCoordinate);
    }
}