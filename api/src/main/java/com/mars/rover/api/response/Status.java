package com.mars.rover.api.response;

import java.io.Serial;
import java.io.Serializable;

import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;

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

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {
        Status.Fields.SUCCESS,
        Status.Fields.STATUS_CODE,
        Status.Fields.STATUS_DESCRIPTION
})

@Schema(name = Status.STATUS_SCHEMA_NAME)
public class Status implements Serializable {

    @Serial
    private static final long serialVersionUID = -3384894274119064560L;

    public static final String STATUS_SCHEMA_NAME = "Status";

    @JsonProperty(Status.Fields.STATUS_CODE)
    @Schema(description = "The status code associated with this status message.")
    private String statusCode;

    @JsonProperty(Status.Fields.SUCCESS)
    @Schema(description = "A boolean flag to indicate the success of the operation.", example = "true")
    private boolean success;

    @JsonProperty(Status.Fields.STATUS_DESCRIPTION)
    @Schema(description = "The status message associated with this status code.")
    private String statusDescription;
}