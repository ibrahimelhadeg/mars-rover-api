package com.mars.rover.core;

import java.util.stream.Stream;

import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class RoverMovingTest {

    @Mock
    private Location mockedLocation;

    private static Stream<Arguments> movingCases() {
        return Stream.of(

                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(1).withY(0).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(1).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(-1).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(-1).withY(0).build(),
                        Command.FORWARD),

                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(-1).withY(0).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(-1).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(1).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(0).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(1).withY(0).build(),
                        Command.BACKWARD)
        );
    }

    private static Stream<Arguments> movingForwardEdgeCases() {
        return Stream.of(

                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),

                Arguments.of(
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),

                Arguments.of(
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD),

                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.FORWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.FORWARD)
        );
    }

    private static Stream<Arguments> movingBackwardEdgeCases() {
        return Stream.of(

                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.EST,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),

                Arguments.of(
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.NORTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD),

                Arguments.of(
                        Location.builder().withX(0).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(0).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.SOUTH,
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),

                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(0).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(0).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_LOWER_BOUNDARY).build(),
                        Command.BACKWARD),
                Arguments.of(
                        Location.builder().withX(Location.X_UPPER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Direction.WEST,
                        Location.builder().withX(Location.X_LOWER_BOUNDARY).withY(Location.Y_UPPER_BOUNDARY).build(),
                        Command.BACKWARD)
        );
    }

    @ParameterizedTest
    @MethodSource({"movingCases", "movingForwardEdgeCases", "movingBackwardEdgeCases"})
    void test_moving_situation(Location initialLocation,
                               Direction direction,
                               Location expectedLocation,
                               Command command) {
        lenient().when(mockedLocation.increaseX()).thenReturn(expectedLocation);
        lenient().when(mockedLocation.increaseY()).thenReturn(expectedLocation);
        lenient().when(mockedLocation.decreaseX()).thenReturn(expectedLocation);
        lenient().when(mockedLocation.decreaseY()).thenReturn(expectedLocation);

        var rover = Rover.builder()
                .withLocation(initialLocation)
                .withDirection(direction)
                .build();

        var movedRover = rover.receive(command);

        assertThat(movedRover).isNotNull();
        assertThat(movedRover.direction()).isEqualTo(direction);
        assertThat(movedRover.location()).isEqualTo(expectedLocation);
    }
}
