package com.mars.rover.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

import static org.mockito.Mockito.mock;

class RoverCreationTest {

    @Test
    void should_create_rover() {
        var inputGrid = mock(Grid.class);
        var inputLocation = mock(Location.class);
        var inputDirection = mock(Direction.class);

        var rover = Rover.builder()
                .withGrid(inputGrid)
                .withLocation(inputLocation)
                .withDirection(inputDirection)
                .build();

        assertThat(rover).isNotNull();
        assertThat(rover.grid()).isEqualTo(inputGrid);
        assertThat(rover.location()).isEqualTo(inputLocation);
        assertThat(rover.direction()).isEqualTo(inputDirection);
    }

    @Test
    void should_not_create_rover_with_null_grid() {
        var roverBuilder =
                Rover.builder()
                        .withLocation(mock(Location.class))
                        .withDirection(mock(Direction.class));

        var thrown = assertThrows(
                Exception.class,
                () -> roverBuilder.withGrid(null),
                "An Exception was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("grid is marked non-null but is null");
    }

    @Test
    void should_not_create_rover_with_null_location() {
        var roverBuilder =
                Rover.builder()
                        .withGrid(mock(Grid.class))
                        .withDirection(mock(Direction.class));

        var thrown = assertThrows(
                Exception.class,
                () -> roverBuilder.withLocation(null),
                "An Exception was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("location is marked non-null but is null");
    }

    @Test
    void should_not_create_rover_with_null_facing_direction() {
        var roverBuilder =
                Rover.builder()
                        .withGrid(mock(Grid.class))
                        .withLocation(mock(Location.class));

        var thrown = assertThrows(
                Exception.class,
                () -> roverBuilder.withDirection(null),
                "An Exception was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("direction is marked non-null but is null");
    }
}
