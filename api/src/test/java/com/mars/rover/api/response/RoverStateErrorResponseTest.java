package com.mars.rover.api.response;

import java.net.URI;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.zalando.problem.Status.SERVICE_UNAVAILABLE;

import static org.assertj.core.api.Assertions.assertThat;

class RoverStateErrorResponseTest {

    private RoverStateErrorResponse roverStateErrorResponse1;
    private RoverStateErrorResponse roverStateErrorResponse2;
    private RoverStateErrorResponse roverStateErrorResponse3;
    private RoverStateErrorResponse roverStateErrorResponse4;

    @BeforeEach
    void prepare() {

        var metadata = Metadata.builder()
                .withDescription("Description")
                .withRequestId("requestId")
                .withRequestTimestamp("requestTimestamp")
                .withResponseId("responseId")
                .withResponseTimestamp("responseTimestamp")
                .withServiceName("serviceName")
                .withServiceVersion("serviceVersion")
                .withStatus(null)
                .build();

        var roverStateErrorResponseBuilder =
                RoverStateErrorResponse.builder()
                        .withType(URI.create("https://mars-rover.io/out-of-reach"))
                        .withTitle("Out of Reach")
                        .withStatus(SERVICE_UNAVAILABLE)
                        .withDetail("Cannot get to the rover")
                        .withInstance(URI.create("mars-rover.io/the_rover"))
                        .withParameters(Collections.singletonMap("param", "paramValue"));

        roverStateErrorResponse1 =
                roverStateErrorResponseBuilder.build();

        roverStateErrorResponse2 =
                roverStateErrorResponseBuilder.build();

        roverStateErrorResponse3 =
                RoverStateErrorResponse.builder()
                        .withMetadata(metadata)
                        .build();

        roverStateErrorResponse4 =
                RoverStateErrorResponse.builder()
                        .build();
    }

    @Test
    void test_equals() {
        assertThat(roverStateErrorResponse1)
                .isEqualTo(roverStateErrorResponse2)
                .isNotEqualTo(roverStateErrorResponse3)
                .isNotEqualTo(roverStateErrorResponse4);
    }

    @Test
    void test_hashCode() {
        assertThat(roverStateErrorResponse1.hashCode())
                .hasSameHashCodeAs(roverStateErrorResponse2.hashCode())
                .doesNotHaveSameHashCodeAs(roverStateErrorResponse3.hashCode())
                .doesNotHaveSameHashCodeAs(roverStateErrorResponse4.hashCode());
    }

    @Test
    void test_toString() {
        assertThat(roverStateErrorResponse1)
                .hasToString(roverStateErrorResponse2.toString())
                .doesNotHaveToString(roverStateErrorResponse3.toString())
                .doesNotHaveToString(roverStateErrorResponse4.toString());
    }
}