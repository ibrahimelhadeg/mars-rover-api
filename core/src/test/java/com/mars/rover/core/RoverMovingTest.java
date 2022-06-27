package com.mars.rover.core;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class RoverMovingTest {

    @Mock
    private Location mockedLocation;

    private static Stream<TestCase> movingCases() {
        return Stream.of(

                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(1).withY(0).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(1).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(-1).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(-1).withY(0).build(),
                        Command.FORWARD),

                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(-1).withY(0).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(-1).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(1).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(1).withY(0).build(),
                        Command.BACKWARD)
        );
    }

    private static Stream<TestCase> movingForwardEdgeCases() {
        return Stream.of(

                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),

                new TestCase(
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),

                new TestCase(
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),

                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD)
        );
    }

    private static Stream<TestCase> movingBackwardEdgeCases() {
        return Stream.of(

                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),

                new TestCase(
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),

                new TestCase(
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),

                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                new TestCase(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD)
        );
    }

    @ParameterizedTest
    @MethodSource({"movingCases", "movingForwardEdgeCases", "movingBackwardEdgeCases"})
    void test_moving_situation(TestCase testCase) {
        lenient().when(mockedLocation.increaseX()).thenReturn(testCase.expectedLocation());
        lenient().when(mockedLocation.increaseY()).thenReturn(testCase.expectedLocation());
        lenient().when(mockedLocation.decreaseX()).thenReturn(testCase.expectedLocation());
        lenient().when(mockedLocation.decreaseY()).thenReturn(testCase.expectedLocation());

        var rover = Rover.builder()
                .withLocation(testCase.initialLocation())
                .withDirection(testCase.direction())
                .build();

        var movedRover = rover.execute(testCase.command());

        assertThat(movedRover).isNotNull();
        assertThat(movedRover.direction()).isEqualTo(testCase.direction());
        assertThat(movedRover.location()).isEqualTo(testCase.expectedLocation());
    }

    private record TestCase(Location initialLocation,
                            Direction direction,
                            Location expectedLocation,
                            Command command) {
    }
}
