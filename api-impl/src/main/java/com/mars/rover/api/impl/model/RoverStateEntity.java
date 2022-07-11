package com.mars.rover.api.impl.model;

import java.time.Instant;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.keyvalue.annotation.KeySpace;

import com.mars.rover.core.Direction;
import com.mars.rover.core.Location;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder(setterPrefix = "with", toBuilder = true)
@KeySpace("roverStates")
public class RoverStateEntity {

    @Id
    Instant updateTimestamp;

    Direction direction;

    Location location;
}
