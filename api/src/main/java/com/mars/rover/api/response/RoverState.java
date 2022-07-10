package com.mars.rover.api.response;

import java.io.Serial;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.mars.rover.core.Direction;

import static io.swagger.v3.parser.util.SchemaTypeUtil.INTEGER_TYPE;

@Getter
@ToString

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)

@Builder(setterPrefix = "with", toBuilder = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@FieldNameConstants

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {
        RoverState.Fields.DIRECTION,
        RoverState.Fields.LOCATION})

@Schema(name = RoverState.ROVER_STATE_SCHEMA_NAME)
public class RoverState {

    @Serial
    private static final long serialVersionUID = -1999991689883562777L;

    public static final String ROVER_STATE_SCHEMA_NAME = "RoverState";

    @JsonProperty(RoverState.Fields.DIRECTION)
    @Schema(required = true,
            implementation = Direction.class,
            enumAsRef = true,
            description = "The last known direction of the rover.")
    Direction direction;

    @JsonProperty(RoverState.Fields.LOCATION)
    @Schema(required = true,
            implementation = Location.class,
            description = "The last known location of the rover.")
    Location location;

    @Getter
    @ToString

    @EqualsAndHashCode
    @AllArgsConstructor(access = AccessLevel.PRIVATE)

    @Builder(setterPrefix = "with", toBuilder = true)
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @FieldNameConstants

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonPropertyOrder(value = {
            Location.Fields.X_COORDINATE, Location.Fields.Y_COORDINATE})

    @Schema(name = RoverState.Location.LOCATION_SCHEMA_NAME)
    public static class Location {

        @Serial
        private static final long serialVersionUID = -1771939609434516204L;

        public static final String LOCATION_SCHEMA_NAME = "Location";

        @JsonProperty(Fields.X_COORDINATE)
        @Schema(required = true,
                type = INTEGER_TYPE,
                description = "The last known X coordinate of the rover.",
                example = "0")
        int xCoordinate;

        @JsonProperty(Fields.Y_COORDINATE)
        @Schema(required = true,
                type = INTEGER_TYPE,
                description = "The last known Y coordinate of the rover.",
                example = "-1")
        int yCoordinate;
    }
}
