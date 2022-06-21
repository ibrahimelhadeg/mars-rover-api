package com.mars.rover;

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.fail;

import static com.mars.rover.Location.*;

class LocationTest {

    public static final String INCREASE_X_METHOD_NAME = "increaseX";
    public static final String INCREASE_Y_METHOD_NAME = "increaseY";
    public static final String DECREASE_X_METHOD_NAME = "decreaseX";
    public static final String DECREASE_Y_METHOD_NAME = "decreaseY";

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
    void should_not_create_location_with_x_out_of_range() {
        var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Location.builder()
                        .withX(6)
                        .withY(5)
                        .build(),
                IllegalArgumentException.class.getSimpleName() + " was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("X must be shall be between -5 and 5");
    }

    @Test
    void should_not_create_location_with_y_out_of_range() {
        var thrown = assertThrows(
                IllegalArgumentException.class,
                () -> Location.builder()
                        .withX(-5)
                        .withY(-6)
                        .build(),
                IllegalArgumentException.class.getSimpleName() + " was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("Y must be shall be between -5 and 5");
    }

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
                X_UPPER_BOUNDARY, 0,
                X_LOWER_BOUNDARY, 0);
    }

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
                X_LOWER_BOUNDARY, 0,
                X_UPPER_BOUNDARY, 0);
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
                0, Y_UPPER_BOUNDARY,
                0, Y_LOWER_BOUNDARY);
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
                0, Y_LOWER_BOUNDARY,
                0, Y_UPPER_BOUNDARY);
    }

    private void should_adjust_location(String testedMethodName,
                                        int givenX,
                                        int givenY,
                                        int expectedX,
                                        int expectedY) {
        var startLocation =
                Location.builder()
                        .withX(givenX)
                        .withY(givenY)
                        .build();
        try {
            var testedMethod = Location.class.getMethod(testedMethodName);
            var resultedLocation = (Location) testedMethod.invoke(startLocation);

            assertThat(resultedLocation).isNotNull();
            assertThat(resultedLocation.x()).isEqualTo(expectedX);
            assertThat(resultedLocation.y()).isEqualTo(expectedY);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
            fail("Exception " + exception);
        }
    }
}
