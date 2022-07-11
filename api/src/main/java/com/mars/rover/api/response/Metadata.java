package com.mars.rover.api.response;

import java.io.Serial;
import java.io.Serializable;

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
@JsonPropertyOrder({
        Metadata.Fields.SERVICE_NAME,
        Metadata.Fields.SERVICE_VERSION,
        Metadata.Fields.STATUS,
        Metadata.Fields.DESCRIPTION,
        Metadata.Fields.REQUEST_ID,
        Metadata.Fields.REQUEST_TIMESTAMP,
        Metadata.Fields.RESPONSE_ID,
        Metadata.Fields.RESPONSE_TIMESTAMP
})

@Schema(name = Metadata.METADATA_SCHEMA_NAME)
public class Metadata implements Serializable {

    @Serial
    private static final long serialVersionUID = 4537081829042146431L;

    public static final String METADATA_SCHEMA_NAME = "Metadata";

    @JsonProperty(Fields.SERVICE_NAME)
    @Schema(description = "The service name that was called.")
    private String serviceName;

    @JsonProperty(Fields.SERVICE_VERSION)
    @Schema(description = "The service version that was called.")
    private String serviceVersion;

    @JsonProperty(Fields.STATUS)
    @Schema(description = "The status of the operation.")
    private Status status;

    @JsonProperty(Fields.DESCRIPTION)
    @Schema(description = "The status code associated with this status message.")
    private String description;

    @JsonProperty(Fields.REQUEST_ID)
    @Schema(description = "The request id associated with this operation.")
    private String requestId;

    @JsonProperty(Fields.REQUEST_TIMESTAMP)
    @Schema(description = "The timestamp that the request was received.")
    private String requestTimestamp;

    @JsonProperty(Fields.RESPONSE_ID)
    @Schema(description = "The response id associated with this operation.")
    private String responseId;

    @JsonProperty(Fields.RESPONSE_TIMESTAMP)
    @Schema(description = "The timestamp that the response was generated.")
    private String responseTimestamp;
}
