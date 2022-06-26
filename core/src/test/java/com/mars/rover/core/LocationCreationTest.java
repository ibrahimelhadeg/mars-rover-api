package com.mars.rover.core;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

class LocationCreationTest {

    @Test
    void should_create_location_in_range() {
        int x = 1, y = 1;
        Location location = Location.builder()
                .withX(x)
                .withY(y)
                .build();

        assertThat(location).isNotNull();
        assertThat(location.x()).isEqualTo(x);
        assertThat(location.y()).isEqualTo(y);
    }

    @Test
    void should_not_create_location_with_x_exceeds_the_upper_limit() {
        should_not_create_location_with_x_out_of_range(6);
    }

    @Test
    void should_not_create_location_with_x_exceeds_the_lower_limit() {
        should_not_create_location_with_x_out_of_range(-6);
    }

    private void should_not_create_location_with_x_out_of_range(int x) {
        var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Location.builder()
                        .withX(x)
                        .withY(5)
                        .build(),
                IllegalArgumentException.class.getSimpleName() + " was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("X must be shall be between -5 and 5");
    }

    @Test
    void should_not_create_location_with_y_exceeds_the_upper_limit() {
        should_not_create_location_with_y_out_of_range(6);
    }

    @Test
    void should_not_create_location_with_y_exceeds_the_lower_limit() {
        should_not_create_location_with_y_out_of_range(-6);
    }

    private void should_not_create_location_with_y_out_of_range(int y) {
        var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Location.builder()
                        .withX(-5)
                        .withY(y)
                        .build(),
                IllegalArgumentException.class.getSimpleName() + " was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("Y must be shall be between -5 and 5");
    }
}
