package com.mars.rover;

import java.text.MessageFormat;

import lombok.Builder;

@Builder(setterPrefix = "with", toBuilder = true)
public record Location(int x, int y) {

    public static final int X_UPPER_BOUNDARY = 5;
    public static final int X_LOWER_BOUNDARY = -5;
    public static final int Y_UPPER_BOUNDARY = 5;
    public static final int Y_LOWER_BOUNDARY = -5;

    /* Override the Lombok builder() method to return
     * our custom builder instead of the Lombok generated builder class */
    public static LocationBuilder builder() {
        return new ValidLocationBuilder();
    }

    public Location increaseX() {
        var newX = (x == X_UPPER_BOUNDARY) ? X_LOWER_BOUNDARY : increase(x);
        return toBuilder().withX(newX).build();
    }

    public Location increaseY() {
        var newY = (y == Y_UPPER_BOUNDARY) ? Y_LOWER_BOUNDARY : increase(y);
        return toBuilder().withY(newY).build();
    }

    private int increase(int coordinate) {
        return coordinate + 1;
    }

    public Location decreaseX() {
        var newX = (x == X_LOWER_BOUNDARY) ? X_UPPER_BOUNDARY : decrease(x);
        return toBuilder().withX(newX).build();
    }

    public Location decreaseY() {
        var newY = (y == Y_LOWER_BOUNDARY) ? Y_UPPER_BOUNDARY : decrease(x);
        return toBuilder().withY(newY).build();
    }

    private int decrease(int coordinate) {
        return coordinate - 1;

    }

    /* Customized builder class, extends the Lombok generated builder class
     * and overrides method implementations */
    private static class ValidLocationBuilder extends LocationBuilder {

        /* Adding validations as part of build() method */
        public Location build() {

            if (super.x < X_LOWER_BOUNDARY || super.x > X_UPPER_BOUNDARY) {
                throw new IllegalArgumentException(
                        MessageFormat.format(
                                "X must be shall be between {0} and {1}",
                                X_LOWER_BOUNDARY, X_UPPER_BOUNDARY));
            }

            if (super.y < Y_LOWER_BOUNDARY || super.y > Y_UPPER_BOUNDARY) {
                throw new IllegalArgumentException(
                        MessageFormat.format(
                                "Y must be shall be between {0} and {1}",
                                Y_LOWER_BOUNDARY, Y_UPPER_BOUNDARY));
            }

            return super.build();
        }
    }
}
