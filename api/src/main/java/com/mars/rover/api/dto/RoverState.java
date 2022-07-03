package com.mars.rover.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.SchemaProperties;
import io.swagger.v3.oas.annotations.media.SchemaProperty;

import com.mars.rover.core.Direction;

import static io.swagger.v3.parser.util.SchemaTypeUtil.INTEGER_TYPE;
import static io.swagger.v3.parser.util.SchemaTypeUtil.STRING_TYPE;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(setterPrefix = "with", toBuilder = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@FieldNameConstants
@Schema(
        name = RoverState.ROVER_STATE_SCHEMA_NAME,
        requiredProperties = {
                RoverState.Fields.DIRECTION,
                RoverState.Fields.LOCATION})
@SchemaProperties(value = {
        @SchemaProperty(
                name = RoverState.Fields.DIRECTION,
                schema = @Schema(
                        type = STRING_TYPE,
                        allowableValues = {"EST", "NORTH", "SOUTH", "WEST"})),
        @SchemaProperty(
                name = RoverState.Fields.LOCATION,
                schema = @Schema(implementation = RoverState.Location.class))})
public class RoverState {

    public static final String ROVER_STATE_SCHEMA_NAME = "RoverState";

    @Schema(enumAsRef = true)
    @NonNull Direction direction;
    @NonNull Location location;

    @Getter
    @ToString
    @EqualsAndHashCode
    @AllArgsConstructor(access = AccessLevel.PRIVATE)
    @Builder(setterPrefix = "with", toBuilder = true)
    @FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
    @FieldNameConstants
    @Schema(
            name = RoverState.Location.LOCATION_SCHEMA_NAME,
            requiredProperties = {
                    Location.Fields.X_COORDINATE,
                    Location.Fields.Y_COORDINATE})
    @SchemaProperties(value = {
            @SchemaProperty(
                    name = Location.Fields.X_COORDINATE,
                    schema = @Schema(type = INTEGER_TYPE, example = "0")),
            @SchemaProperty(
                    name = Location.Fields.Y_COORDINATE,
                    schema = @Schema(type = INTEGER_TYPE, example = "-1"))})
    public static class Location {

        public static final String LOCATION_SCHEMA_NAME = "Location";

        int xCoordinate;
        int yCoordinate;
    }
}
