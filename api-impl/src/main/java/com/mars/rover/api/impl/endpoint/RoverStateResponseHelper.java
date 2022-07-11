package com.mars.rover.api.impl.endpoint;

import java.time.Instant;
import java.util.UUID;

import lombok.experimental.UtilityClass;

import org.springframework.http.HttpStatus;

import com.mars.rover.api.response.Metadata;
import com.mars.rover.api.response.Status;

import static com.mars.rover.api.impl.MarsRoverApplicationConstants.SERVICE_NAME;
import static com.mars.rover.api.impl.MarsRoverApplicationConstants.SERVICE_VERSION;

@UtilityClass
public class RoverStateResponseHelper {

    static Status createSuccessStatus() {
        return createSuccessStatus(HttpStatus.OK.getReasonPhrase());
    }

    static Status createSuccessStatus(String statusCode) {
        return Status.builder()
                .withSuccess(true)
                .withStatusCode(statusCode)
                .build();
    }

    static Metadata createRequestMetadata(Status status) {
        return Metadata.builder()
                .withRequestId(UUID.randomUUID().toString())
                .withRequestTimestamp("" + Instant.now().toEpochMilli())
                .withServiceName(SERVICE_NAME)
                .withServiceVersion(SERVICE_VERSION)
                .withStatus(status)
                .build();
    }

    static Metadata amendWithResponseParameters(Metadata metadata) {
        return metadata.toBuilder()
                .withResponseId(UUID.randomUUID().toString())
                .withResponseTimestamp("" + Instant.now().toEpochMilli())
                .build();
    }
}
