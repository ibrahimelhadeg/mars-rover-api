package com.mars.rover.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

class ObstacleCreationTest {

    @Test
    void should_construct_obstacle() {
        Location inputLocation = Location.builder().build();

        Obstacle obstacle = new Obstacle(inputLocation);

        assertThat(obstacle).isNotNull();
        assertThat(obstacle.location()).isEqualTo(inputLocation);
    }

    @Test
    void should_create_obstacle() {
        Location inputLocation = Location.builder().build();

        Obstacle obstacle = Obstacle.builder()
                .withLocation(inputLocation)
                .build();

        assertThat(obstacle).isNotNull();
        assertThat(obstacle.location()).isEqualTo(inputLocation);
    }

    @Test
    void should_not_create_obstacle_with_null_location() {
        var thrown = assertThrows(
                Exception.class,
                () -> Obstacle.builder()
                        .withLocation(null)
                        .build(),
                "An Exception was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("location is marked non-null but is null");
    }
}
