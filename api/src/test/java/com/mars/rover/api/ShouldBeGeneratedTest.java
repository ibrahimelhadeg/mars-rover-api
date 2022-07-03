package com.mars.rover.api;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.swagger.v3.oas.models.OpenAPI;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldBeGeneratedTest {

    private static OpenAPI ACTUAL_API_CONTRACT;

    @BeforeAll
    static void getGeneratedApiContract() {
        ACTUAL_API_CONTRACT = GeneratedApiContractExtension.getGeneratedApiContract();
    }

    @Test
    void should_provide_api_contract() {
        assertThat(ACTUAL_API_CONTRACT).isNotNull();
    }
}
