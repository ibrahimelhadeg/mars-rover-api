package com.mars.rover.core;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class RoverTurningTest {

    @Mock
    private Grid mockedGrid;
    @Mock
    private Location mockedLocation;

    @ParameterizedTest
    @MethodSource("turningCases")
    void test_turning_situation(TestCase testCase) {
        var rover = Rover.builder()
                .withGrid(mockedGrid)
                .withLocation(mockedLocation)
                .withDirection(testCase.initialDirection())
                .build();

        var turnedRover = rover.execute(testCase.command());

        assertThat(turnedRover).isNotNull();
        assertThat(turnedRover.location())
                .isEqualTo(rover.location());
        assertThat(turnedRover.direction())
                .isEqualTo(testCase.expectedDirection());
    }

    private static Stream<TestCase> turningCases() {
        return Stream.of(
                new TestCase(Direction.NORTH, Direction.WEST, Command.LEFT),
                new TestCase(Direction.NORTH, Direction.EST, Command.RIGHT),
                new TestCase(Direction.SOUTH, Direction.EST, Command.LEFT),
                new TestCase(Direction.SOUTH, Direction.WEST, Command.RIGHT),
                new TestCase(Direction.WEST, Direction.SOUTH, Command.LEFT),
                new TestCase(Direction.WEST, Direction.NORTH, Command.RIGHT),
                new TestCase(Direction.EST, Direction.NORTH, Command.LEFT),
                new TestCase(Direction.EST, Direction.SOUTH, Command.RIGHT)
        );
    }

    private record TestCase(Direction initialDirection,
                            Direction expectedDirection,
                            Command command) {
    }
}
