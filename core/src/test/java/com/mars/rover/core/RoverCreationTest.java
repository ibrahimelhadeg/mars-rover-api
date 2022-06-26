package com.mars.rover.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

class RoverCreationTest {

    @Test
    void should_create_rover() {
        var inputLocation = Location.builder().build();
        var inputDirection = Direction.NORTH;

        var rover = Rover.builder()
                .withLocation(inputLocation)
                .withDirection(inputDirection)
                .build();

        assertThat(rover).isNotNull();
        assertThat(rover.location()).isEqualTo(inputLocation);
        assertThat(rover.direction()).isEqualTo(inputDirection);
    }

    @Test
    void should_not_create_rover_with_null_location() {
        var thrown = assertThrows(
                Exception.class,
                () -> Rover.builder()
                        .withLocation(null)
                        .withDirection(Direction.NORTH)
                        .build(),
                "An Exception was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("location is marked non-null but is null");
    }

    @Test
    void should_not_create_rover_with_null_facing_direction() {
        var thrown = assertThrows(
                Exception.class,
                () -> Rover.builder()
                        .withLocation(Location.builder().build())
                        .withDirection(null)
                        .build(),
                "An Exception was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("direction is marked non-null but is null");
    }
}
