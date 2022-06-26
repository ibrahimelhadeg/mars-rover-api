package com.mars.rover.core;

import org.junit.jupiter.api.Test;

class LocationIncreaseTest extends LocationAbstractTest {

    public static final String INCREASE_X_METHOD_NAME = "increaseX";
    public static final String INCREASE_Y_METHOD_NAME = "increaseY";

    @Test
    void should_increase_x_on_positive_range() {
        should_adjust_location(
                INCREASE_X_METHOD_NAME,
                3, 3,
                4, 3);
    }

    @Test
    void should_increase_x_on_negative_range() {
        should_adjust_location(
                INCREASE_X_METHOD_NAME,
                -3, -3,
                -2, -3);
    }

    @Test
    void should_increase_x_at_origin() {
        should_adjust_location(
                INCREASE_X_METHOD_NAME,
                0, 0,
                1, 0);
    }

    @Test
    void should_wrap_x_to_lower_boundary_when_increasing_x_at_upper_boundary() {
        should_adjust_location(
                INCREASE_X_METHOD_NAME,
                Location.X_UPPER_BOUNDARY, 0,
                Location.X_LOWER_BOUNDARY, 0);
    }

    @Test
    void should_increase_y_on_positive_range() {
        should_adjust_location(
                INCREASE_Y_METHOD_NAME,
                3, 3,
                3, 4);
    }

    @Test
    void should_increase_y_on_negative_range() {
        should_adjust_location(
                INCREASE_Y_METHOD_NAME,
                -3, -3,
                -3, -2);
    }

    @Test
    void should_increase_y_at_origin() {
        should_adjust_location(
                INCREASE_Y_METHOD_NAME,
                0, 0,
                0, 1);
    }

    @Test
    void should_wrap_y_to_lower_boundary_when_increasing_y_at_upper_boundary() {
        should_adjust_location(
                INCREASE_Y_METHOD_NAME,
                0, Location.Y_UPPER_BOUNDARY,
                0, Location.Y_LOWER_BOUNDARY);
    }
}
