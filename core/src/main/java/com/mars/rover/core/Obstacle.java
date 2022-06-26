package com.mars.rover.core;

import lombok.Builder;
import lombok.NonNull;

@Builder(setterPrefix = "with", toBuilder = true)
public record Obstacle(@NonNull Location location) {
}
