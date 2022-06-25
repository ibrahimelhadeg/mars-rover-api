package com.mars.rover.core;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static org.assertj.core.api.Assertions.assertThat;

class RoverTurningTest {

    private Location testLocation;

    private static Stream<Arguments> turningCases() {
        return Stream.of(
                Arguments.of(
                        Direction.NORTH, Direction.WEST, Command.LEFT),
                Arguments.of(
                        Direction.NORTH, Direction.EST, Command.RIGHT),
                Arguments.of(
                        Direction.SOUTH, Direction.EST, Command.LEFT),
                Arguments.of(
                        Direction.SOUTH, Direction.WEST, Command.RIGHT),
                Arguments.of(
                        Direction.WEST, Direction.SOUTH, Command.LEFT),
                Arguments.of(
                        Direction.WEST, Direction.NORTH, Command.RIGHT),
                Arguments.of(
                        Direction.EST, Direction.NORTH, Command.LEFT),
                Arguments.of(
                        Direction.EST, Direction.SOUTH, Command.RIGHT)
        );
    }

    @BeforeEach
    void prepare_test_location() {
        testLocation = Location.builder()
                .withX(0)
                .withY(0)
                .build();
    }

    @ParameterizedTest
    @MethodSource("turningCases")
    void test_turning_situation(Direction initialDirection,
                                Direction expectedDirection,
                                Command command) {
        var rover = Rover.builder()
                .withLocation(testLocation)
                .withDirection(initialDirection)
                .build();

        var turnedRover = rover.receive(command);

        assertThat(turnedRover).isNotNull();
        assertThat(turnedRover.getLocation())
                .isEqualTo(rover.getLocation());
        assertThat(turnedRover.getDirection())
                .isEqualTo(expectedDirection);
    }
}
