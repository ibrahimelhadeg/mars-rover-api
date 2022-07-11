package com.mars.rover.api.response;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatusTest {

    private Status status1;
    private Status status2;
    private Status status3;

    @BeforeEach
    void prepare() {

        status1 = Status.builder()
                .withSuccess(Boolean.TRUE)
                .withStatusCode("StatusCode")
                .withStatusDescription("StatusMessage")
                .build();

        status2 = Status.builder()
                .withSuccess(Boolean.TRUE)
                .withStatusCode("StatusCode")
                .withStatusDescription("StatusMessage")
                .build();

        status3 = Status.builder()
                .withSuccess(Boolean.FALSE)
                .withStatusCode("StatusCode3")
                .withStatusDescription("StatusMessage3")
                .build();
    }

    @Test
    void test_equals() {
        assertThat(status1)
                .isEqualTo(status2)
                .isNotEqualTo(status3);
    }

    @Test
    void test_hashCode() {
        assertThat(status1.hashCode())
                .hasSameHashCodeAs(status2.hashCode())
                .doesNotHaveSameHashCodeAs(status3.hashCode());
    }

    @Test
    void test_toString() {
        assertThat(status1)
                .hasToString(status2.toString())
                .doesNotHaveToString(status3.toString());
    }
}