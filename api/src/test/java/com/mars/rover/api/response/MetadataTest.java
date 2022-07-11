package com.mars.rover.api.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MetadataTest {

    private Metadata metadata1;
    private Metadata metadata2;
    private Metadata metadata3;
    private Metadata metadata4;

    @BeforeEach
    void prepare() {

        metadata1 = Metadata.builder()
                .withDescription("Description")
                .withRequestId("requestId")
                .withRequestTimestamp("requestTimestamp")
                .withResponseId("responseId")
                .withResponseTimestamp("responseTimestamp")
                .withServiceName("serviceName")
                .withServiceVersion("serviceVersion")
                .withStatus(null)
                .build();

        metadata2 = Metadata.builder()
                .withDescription("Description")
                .withRequestId("requestId")
                .withRequestTimestamp("requestTimestamp")
                .withResponseId("responseId")
                .withResponseTimestamp("responseTimestamp")
                .withServiceName("serviceName")
                .withServiceVersion("serviceVersion")
                .withStatus(null)
                .build();

        metadata3 = Metadata.builder()
                .withDescription("Description3")
                .withRequestId("requestId3")
                .withRequestTimestamp("requestTimestamp3")
                .withResponseId("responseId3")
                .withResponseTimestamp("responseTimestamp3")
                .withServiceName("serviceName3")
                .withServiceVersion("serviceVersion3")
                .withStatus(null)
                .build();

        metadata4 = Metadata.builder()
                .withDescription("Description3")
                .withRequestId("requestId3")
                .withRequestTimestamp(null)
                .withResponseId("responseId3")
                .withResponseTimestamp(null)
                .withServiceName("serviceName3")
                .withServiceVersion("serviceVersion3")
                .withStatus(null)
                .build();
    }

    @Test
    void test_equals() {
        assertThat(metadata1)
                .isEqualTo(metadata2)
                .isNotEqualTo(metadata3)
                .isNotEqualTo(metadata4);
    }

    @Test
    void test_hashCode() {
        assertThat(metadata1.hashCode())
                .hasSameHashCodeAs(metadata2.hashCode())
                .doesNotHaveSameHashCodeAs(metadata3.hashCode())
                .doesNotHaveSameHashCodeAs(metadata4.hashCode());
    }

    @Test
    void test_toString() {
        assertThat(metadata1)
                .hasToString(metadata2.toString())
                .doesNotHaveToString(metadata3.toString())
                .doesNotHaveToString(metadata4.toString());
    }
}