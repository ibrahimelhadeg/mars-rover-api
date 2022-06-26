package com.mars.rover.core;

import org.junit.jupiter.api.Test;

class LocationDecreaseTest extends LocationAbstractTest {

    public static final String DECREASE_X_METHOD_NAME = "decreaseX";
    public static final String DECREASE_Y_METHOD_NAME = "decreaseY";

    @Test
    void should_decrease_x_on_positive_range() {
        should_adjust_location(
                DECREASE_X_METHOD_NAME,
                3, 3,
                2, 3);
    }

    @Test
    void should_decrease_x_on_negative_range() {
        should_adjust_location(
                DECREASE_X_METHOD_NAME,
                -3, -3,
                -4, -3);
    }

    @Test
    void should_decrease_x_at_origin() {
        should_adjust_location(
                DECREASE_X_METHOD_NAME,
                0, 0,
                -1, 0);
    }

    @Test
    void should_wrap_x_to_upper_boundary_when_decreasing_x_at_lower_boundary() {
        should_adjust_location(
                DECREASE_X_METHOD_NAME,
                Location.X_LOWER_BOUNDARY, 0,
                Location.X_UPPER_BOUNDARY, 0);
    }

    @Test
    void should_decrease_y_on_positive_range() {
        should_adjust_location(
                DECREASE_Y_METHOD_NAME,
                3, 3,
                3, 2);
    }

    @Test
    void should_decrease_y_on_negative_range() {
        should_adjust_location(
                DECREASE_Y_METHOD_NAME,
                -3, -3,
                -3, -4);
    }

    @Test
    void should_decrease_y_at_origin() {
        should_adjust_location(
                DECREASE_Y_METHOD_NAME,
                0, 0,
                0, -1);
    }

    @Test
    void should_wrap_y_to_upper_boundary_when_decreasing_y_at_lower_boundary() {
        should_adjust_location(
                DECREASE_Y_METHOD_NAME,
                0, Location.Y_LOWER_BOUNDARY,
                0, Location.Y_UPPER_BOUNDARY);
    }
}
