package com.mars.rover.core;

import java.util.Set;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with", toBuilder = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class Grid {

    @NonNull Set<Location> obstacles;

    public boolean isFree(Location location) {
        return !obstacles.contains(location);
    }
}
