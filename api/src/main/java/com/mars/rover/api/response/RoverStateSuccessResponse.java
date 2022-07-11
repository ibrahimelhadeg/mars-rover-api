package com.mars.rover.api.response;

import java.io.Serial;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.extern.jackson.Jacksonized;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@Getter
@ToString

@EqualsAndHashCode
@AllArgsConstructor(access = AccessLevel.PRIVATE)

@Builder(setterPrefix = "with", toBuilder = true)
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@FieldNameConstants

@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {
        RoverStateSuccessResponse.Fields.METADATA,
        RoverStateSuccessResponse.Fields.STATE})

@Schema(name = RoverStateSuccessResponse.ROVER_STATE_SUCCESS_RESPONSE_SCHEMA_NAME)
public class RoverStateSuccessResponse implements RoverStateResponse {

    @Serial
    private static final long serialVersionUID = -6220322296496963477L;

    public static final String ROVER_STATE_SUCCESS_RESPONSE_SCHEMA_NAME = "RoverStateSuccessResponse";

    @JsonProperty(RoverStateSuccessResponse.Fields.METADATA)
    @Schema(description = "Container for any metadata for this request/response")
    Metadata metadata;

    @JsonProperty(RoverStateSuccessResponse.Fields.STATE)
    @Schema(description = "A single rover state returned as a payload")
    RoverState state;
}
