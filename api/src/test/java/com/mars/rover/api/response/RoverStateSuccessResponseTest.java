package com.mars.rover.api.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.mars.rover.core.Direction;

import static org.assertj.core.api.Assertions.assertThat;

class RoverStateSuccessResponseTest {

    private RoverStateSuccessResponse roverStateSuccessResponse1;
    private RoverStateSuccessResponse roverStateSuccessResponse2;
    private RoverStateSuccessResponse roverStateSuccessResponse3;
    private RoverStateSuccessResponse roverStateSuccessResponse4;

    @BeforeEach
    void prepare() {

        var roverState1 = RoverState.builder()
                .withDirection(Direction.EST)
                .withLocation(
                        RoverState.Location
                                .builder()
                                .withXCoordinate(1)
                                .withYCoordinate(-1)
                                .build())
                .build();

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

        roverStateSuccessResponse1 =
                RoverStateSuccessResponse.builder()
                        .withState(roverState1)
                        .build();

        roverStateSuccessResponse2 =
                RoverStateSuccessResponse.builder()
                        .withState(roverState1)
                        .build();

        roverStateSuccessResponse3 =
                RoverStateSuccessResponse.builder()
                        .withMetadata(metadata1)
                        .build();

        roverStateSuccessResponse4 =
                RoverStateSuccessResponse.builder()
                        .build();
    }

    @Test
    void test_equals() {
        assertThat(roverStateSuccessResponse1)
                .isEqualTo(roverStateSuccessResponse2);

        assertThat(roverStateSuccessResponse1)
                .isNotEqualTo(roverStateSuccessResponse3);

        assertThat(roverStateSuccessResponse1)
                .isNotEqualTo(roverStateSuccessResponse4);
    }

    @Test
    void test_hashCode() {
        assertThat(roverStateSuccessResponse1.hashCode())
                .isEqualTo(roverStateSuccessResponse2.hashCode());

        assertThat(roverStateSuccessResponse1.hashCode())
                .isNotEqualTo(roverStateSuccessResponse3.hashCode());

        assertThat(roverStateSuccessResponse1.hashCode())
                .isNotEqualTo(roverStateSuccessResponse4.hashCode());
    }

    @Test
    void test_toString() {
        assertThat(roverStateSuccessResponse1.toString())
                .isEqualTo(roverStateSuccessResponse2.toString());

        assertThat(roverStateSuccessResponse1.toString())
                .isNotEqualTo(roverStateSuccessResponse3.toString());

        assertThat(roverStateSuccessResponse1.toString())
                .isNotEqualTo(roverStateSuccessResponse4.toString());
    }
}