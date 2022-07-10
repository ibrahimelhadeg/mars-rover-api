package com.mars.rover.api.response;

import java.net.URI;
import java.util.Collections;

import org.zalando.problem.Problem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.zalando.problem.Status.GATEWAY_TIMEOUT;
import static org.zalando.problem.Status.SERVICE_UNAVAILABLE;

import static org.assertj.core.api.Assertions.assertThat;

class RoverStateErrorResponseTest {

    private RoverStateErrorResponse roverStateErrorResponse1;
    private RoverStateErrorResponse roverStateErrorResponse2;
    private RoverStateErrorResponse roverStateErrorResponse3;
    private RoverStateErrorResponse roverStateErrorResponse4;

    @BeforeEach
    void prepare() {

        var metadata1 = Metadata.builder()
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
                        .withCause(Problem.builder()
                                .withType(URI.create("mars-rover.io/lost_connection"))
                                .withTitle("Out of Stock")
                                .withStatus(GATEWAY_TIMEOUT)
                                .build())
                        .withParameters(Collections.singletonMap("param", "paramValue"));

        roverStateErrorResponse1 =
                roverStateErrorResponseBuilder.build();

        roverStateErrorResponse2 =
                roverStateErrorResponseBuilder.build();

        roverStateErrorResponse3 =
                RoverStateErrorResponse.builder()
                        .withMetadata(metadata1)
                        .build();

        roverStateErrorResponse4 =
                RoverStateErrorResponse.builder()
                        .build();
    }

    @Test
    void test_equals() {
        assertThat(roverStateErrorResponse1)
                .isEqualTo(roverStateErrorResponse2);

        assertThat(roverStateErrorResponse1)
                .isNotEqualTo(roverStateErrorResponse3);

        assertThat(roverStateErrorResponse1)
                .isNotEqualTo(roverStateErrorResponse4);
    }

    @Test
    void test_hashCode() {
        assertThat(roverStateErrorResponse1.hashCode())
                .isEqualTo(roverStateErrorResponse2.hashCode());

        assertThat(roverStateErrorResponse1.hashCode())
                .isNotEqualTo(roverStateErrorResponse3.hashCode());

        assertThat(roverStateErrorResponse1.hashCode())
                .isNotEqualTo(roverStateErrorResponse4.hashCode());
    }

    @Test
    void test_toString() {
        assertThat(roverStateErrorResponse1.toString())
                .isEqualTo(roverStateErrorResponse2.toString());

        assertThat(roverStateErrorResponse1.toString())
                .isNotEqualTo(roverStateErrorResponse3.toString());

        assertThat(roverStateErrorResponse1.toString())
                .isNotEqualTo(roverStateErrorResponse4.toString());
    }
}