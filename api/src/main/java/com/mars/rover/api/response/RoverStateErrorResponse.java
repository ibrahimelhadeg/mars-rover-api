package com.mars.rover.api.response;

import java.io.Serial;
import java.net.URI;
import java.util.Map;
import java.util.Objects;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.extern.jackson.Jacksonized;

import io.swagger.v3.oas.annotations.media.Schema;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.StatusType;

@Getter
@ToString(callSuper = true)

@AllArgsConstructor(access = AccessLevel.PRIVATE)

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@FieldNameConstants

@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder(value = {
        RoverStateSuccessResponse.Fields.METADATA,
        "type",
        "title",
        "status",
        "detail",
        "instance",
        "parameters",
        "cause"})

@Schema(name = RoverStateErrorResponse.ROVER_STATE_ERROR_RESPONSE_SCHEMA_NAME)
public class RoverStateErrorResponse extends AbstractThrowableProblem implements RoverStateResponse {

    @Serial
    private static final long serialVersionUID = -6220322296496963477L;

    public static final String ROVER_STATE_ERROR_RESPONSE_SCHEMA_NAME = "RoverStateErrorResponse";

    @JsonProperty(RoverStateErrorResponse.Fields.METADATA)
    @Schema(description = "Container for any metadata for this request/response")
    private Metadata metadata;

    private RoverStateErrorResponse(URI type,
                                    String title,
                                    StatusType status,
                                    String detail,
                                    URI instance,
                                    Map<String, Object> parameters,
                                    Metadata metadata) {
        super(type, title, status, detail, instance, null, parameters);
        this.metadata = metadata;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoverStateErrorResponse that = (RoverStateErrorResponse) o;
        return Objects.equals(getType(), that.getType()) &&
                Objects.equals(getTitle(), that.getTitle()) &&
                Objects.equals(getStatus(), that.getStatus()) &&
                Objects.equals(getDetail(), that.getDetail()) &&
                Objects.equals(getInstance(), that.getInstance()) &&
                Objects.equals(getCause(), that.getCause()) &&
                Objects.equals(getParameters(), that.getParameters()) &&
                Objects.equals(metadata, that.metadata);
    }

    @Override
    public int hashCode() {
        int result = getType() != null ? getType().hashCode() : 0;
        result = 31 * result + (getTitle() != null ? getTitle().hashCode() : 0);
        result = 31 * result + (getStatus() != null ? getStatus().hashCode() : 0);
        result = 31 * result + (getDetail() != null ? getDetail().hashCode() : 0);
        result = 31 * result + (getInstance() != null ? getInstance().hashCode() : 0);
        result = 31 * result + (getCause() != null ? getCause().hashCode() : 0);
        result = 31 * result + (getParameters() != null ? getParameters().hashCode() : 0);
        result = 31 * result + (metadata != null ? metadata.hashCode() : 0);
        return result;
    }

    public static RoverStateErrorResponseBuilder builder() {
        return new RoverStateErrorResponseBuilder();
    }

    public static class RoverStateErrorResponseBuilder {

        private URI type;
        private String title;
        private StatusType status;
        private String detail;
        private URI instance;
        private Map<String, Object> parameters;
        private Metadata metadata;

        public RoverStateErrorResponseBuilder withType(URI type) {
            this.type = type;
            return this;
        }

        public RoverStateErrorResponseBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public RoverStateErrorResponseBuilder withStatus(StatusType status) {
            this.status = status;
            return this;
        }

        public RoverStateErrorResponseBuilder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public RoverStateErrorResponseBuilder withInstance(URI instance) {
            this.instance = instance;
            return this;
        }

        public RoverStateErrorResponseBuilder withParameters(Map<String, Object> parameters) {
            this.parameters = parameters;
            return this;
        }

        public RoverStateErrorResponseBuilder withMetadata(Metadata metadata) {
            this.metadata = metadata;
            return this;
        }

        /* Adding validations as part of build() method */
        public RoverStateErrorResponse build() {
            return new RoverStateErrorResponse(
                    type, title, status, detail, instance, parameters, metadata);
        }
    }
}
