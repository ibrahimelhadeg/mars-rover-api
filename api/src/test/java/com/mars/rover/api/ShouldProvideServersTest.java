package com.mars.rover.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.ApiContractConstants.*;
import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldProvideServersTest {

    @Test
    void should_provide_api_hostnames_format() {
        var apiHostnames = getGeneratedApiContract().getServers();

        assertThat(apiHostnames).isNotNull().hasSize(1);

        assertThat(apiHostnames.get(0).getUrl()).isEqualTo(API_SERVER_URL_FORMAT);
    }

    @Test
    void should_describe_the_environment_variable() {
        var variables =
                getGeneratedApiContract()
                        .getServers()
                        .get(0)
                        .getVariables();
        assertThat(variables).isNotNull().hasSize(1);

        var environmentVariable =
                variables.get(API_SERVER_ENVIRONMENT_VARIABLE_NAME);
        assertThat(environmentVariable).isNotNull();
        assertThat(environmentVariable.getDescription())
                .isEqualTo(API_SERVER_ENVIRONMENT_VARIABLE_DESCRIPTION);
        assertThat(environmentVariable.getDefault())
                .isEqualTo(API_SERVER_ENVIRONMENT_VARIABLE_PRODUCTION_VALUE);

        var allowedValues = environmentVariable.getEnum();
        assertThat(allowedValues)
                .isNotNull()
                .hasSize(3)
                .anyMatch(API_SERVER_ENVIRONMENT_VARIABLE_DEVELOPMENT_VALUE::equals)
                .anyMatch(API_SERVER_ENVIRONMENT_VARIABLE_STAGING_VALUE::equals)
                .anyMatch(API_SERVER_ENVIRONMENT_VARIABLE_PRODUCTION_VALUE::equals);
    }
}
