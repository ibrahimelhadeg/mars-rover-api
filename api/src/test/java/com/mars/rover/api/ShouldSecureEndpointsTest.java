package com.mars.rover.api;

import java.util.List;

import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.ApiContractConstants.*;
import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;
import static com.mars.rover.api.RoverStateApiContract.ROVER_STATE_PATH;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldSecureEndpointsTest {

    @Test
    void should_provide_security_schema() {
        assertThat(getGeneratedApiContract().getSecurity()).isNotNull();

        assertThat(getGeneratedApiContract().getSecurity()).hasSize(1);
    }

    @Test
    void should_use_bearer_authentication() {
        var singleSecurityRequirement =
                getGeneratedApiContract().getSecurity().get(0);

        assertThat(singleSecurityRequirement).isNotNull();


        assertThat(singleSecurityRequirement.get(API_SECURITY_SCHEMA_NAME)).isNotNull();
        assertThat(singleSecurityRequirement.get(API_SECURITY_SCHEMA_NAME)).isEmpty();
    }

    @Test
    void should_provide_security_schema_details() {
        var securitySchemes =
                getGeneratedApiContract().getComponents().getSecuritySchemes();
        assertThat(securitySchemes).isNotNull();

        var bearerAuthSecurityScheme = securitySchemes.get(API_SECURITY_SCHEMA_NAME);
        assertThat(bearerAuthSecurityScheme).isNotNull();

        assertThat(bearerAuthSecurityScheme.getType()).isEqualTo(SecurityScheme.Type.HTTP);
        assertThat(bearerAuthSecurityScheme.getIn()).isEqualTo(SecurityScheme.In.HEADER);
        assertThat(bearerAuthSecurityScheme.getScheme()).isEqualTo(API_SECURITY_MECHANISM);
        assertThat(bearerAuthSecurityScheme.getBearerFormat()).isEqualTo(API_SECURITY_BEARER_FORMAT);
    }

    @Test
    void should_secure_get_rover_state_endpoint() {
        var getRoverStateOperationSecurityRequirements =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getGet()
                        .getSecurity();
        check_endpoint_security_requirements(getRoverStateOperationSecurityRequirements);
    }

    @Test
    void should_secure_post_rover_state_endpoint() {
        var postRoverStateOperationSecurityRequirements =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getPost()
                        .getSecurity();
        check_endpoint_security_requirements(postRoverStateOperationSecurityRequirements);
    }

    private void check_endpoint_security_requirements(
            final List<SecurityRequirement> securityRequirements) {
        assertThat(securityRequirements).isNotNull();
        assertThat(securityRequirements).hasSize(1);

        assertThat(securityRequirements.get(0)).isNotNull();
        assertThat(securityRequirements.get(0).get(API_SECURITY_SCHEMA_NAME)).isNotNull();
    }
}
