package com.mars.rover.api.impl;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class MarsRoverApplicationTests {

    @Test
    void should_load_context(ApplicationContext context) {
        assertThat(context).isNotNull();
    }
}
