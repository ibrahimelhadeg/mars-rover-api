package com.mars.rover.core;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with", toBuilder = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Rover {

    @NonNull Grid grid;
    @NonNull Location location;
    @NonNull Direction direction;

    public Rover execute(@NonNull Command command) {
        return switch (command) {
            case LEFT -> turnLeft();
            case RIGHT -> turnRight();
            case BACKWARD -> moveBackward();
            case FORWARD -> moveForward();
        };
    }

    private Rover turnLeft() {
        return switch (direction) {
            case EST -> toBuilder()
                    .withDirection(Direction.NORTH)
                    .build();
            case NORTH -> toBuilder()
                    .withDirection(Direction.WEST)
                    .build();
            case SOUTH -> toBuilder()
                    .withDirection(Direction.EST)
                    .build();
            case WEST -> toBuilder()
                    .withDirection(Direction.SOUTH)
                    .build();
        };
    }

    private Rover turnRight() {
        return switch (direction) {
            case EST -> toBuilder()
                    .withDirection(Direction.SOUTH)
                    .build();
            case NORTH -> toBuilder()
                    .withDirection(Direction.EST)
                    .build();
            case SOUTH -> toBuilder()
                    .withDirection(Direction.WEST)
                    .build();
            case WEST -> toBuilder()
                    .withDirection(Direction.NORTH)
                    .build();
        };
    }

    private Rover moveForward() {
        var nextLocation = switch (direction) {
            case EST -> location.increaseX();
            case NORTH -> location.increaseY();
            case SOUTH -> location.decreaseY();
            case WEST -> location.decreaseX();
        };

        if (!grid.isFree(nextLocation)) {
            return this;
        } else {
            return switch (direction) {
                case EST -> toBuilder()
                        .withLocation(location.increaseX())
                        .build();
                case NORTH -> toBuilder()
                        .withLocation(location.increaseY())
                        .build();
                case SOUTH -> toBuilder()
                        .withLocation(location.decreaseY())
                        .build();
                case WEST -> toBuilder()
                        .withLocation(location.decreaseX())
                        .build();
            };
        }
    }

    private Rover moveBackward() {
        return switch (direction) {
            case EST -> toBuilder()
                    .withLocation(location.decreaseX())
                    .build();
            case NORTH -> toBuilder()
                    .withLocation(location.decreaseY())
                    .build();
            case SOUTH -> toBuilder()
                    .withLocation(location.increaseY())
                    .build();
            case WEST -> toBuilder()
                    .withLocation(location.increaseX())
                    .build();
        };
    }
}
