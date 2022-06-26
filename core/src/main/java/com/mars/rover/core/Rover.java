package com.mars.rover.core;

import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;

@Getter
@Builder(setterPrefix = "with", toBuilder = true)
public class Rover {

    @NonNull Location location;
    @NonNull Direction direction;

    public Rover receive(@NonNull Command command) {
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
