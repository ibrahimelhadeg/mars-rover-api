package com.mars.rover.api;

import io.swagger.v3.oas.models.Operation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.ApiContractConstants.*;
import static com.mars.rover.api.ApiContractTestUtil.SCHEMAS_STANDARD_PATH;
import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;
import static com.mars.rover.api.RoverStateApiContract.ROVER_STATE_PATH;
import static com.mars.rover.api.response.RoverState.ROVER_STATE_SCHEMA_NAME;
import static com.mars.rover.api.response.RoverStateErrorResponse.ROVER_STATE_ERROR_RESPONSE_SCHEMA_NAME;
import static com.mars.rover.api.response.RoverStateSuccessResponse.ROVER_STATE_SUCCESS_RESPONSE_SCHEMA_NAME;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldExposeRoverStateApiContractTest {

    public static final String ROVER_STATE_SUCCESS_RESPONSE_SCHEMA_PATH =
            SCHEMAS_STANDARD_PATH + ROVER_STATE_SUCCESS_RESPONSE_SCHEMA_NAME;

    public static final String ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH =
            SCHEMAS_STANDARD_PATH + ROVER_STATE_ERROR_RESPONSE_SCHEMA_NAME;

    @Test
    void should_provide_api_endpoints() {
        assertThat(getGeneratedApiContract().getPaths()).isNotNull();

        assertThat(getGeneratedApiContract().getPaths()).hasSize(1);
    }

    @Test
    void should_provide_rover_state_endpoint() {
        var roverStateEndpoint =
                getGeneratedApiContract().getPaths().get(ROVER_STATE_PATH);

        assertThat(roverStateEndpoint).isNotNull();
    }

    @Test
    void should_have_get_operation_on_rover_state_endpoint_tagged_for_commanders() {
        var roverStateGetOperation =
                getGeneratedApiContract().getPaths().get(ROVER_STATE_PATH).getGet();

        assertThat(roverStateGetOperation).isNotNull();

        assertThat(roverStateGetOperation.getDescription())
                .isEqualTo(ROVER_STATE_RETRIEVE_OPERATION_DESCRIPTION);

        assertThat(roverStateGetOperation.getOperationId())
                .isEqualTo(ROVER_STATE_RETRIEVE_OPERATION_ID);

        assertThat(roverStateGetOperation.getSummary())
                .isEqualTo(ROVER_STATE_RETRIEVE_OPERATION_SUMMARY);

        var tags = roverStateGetOperation.getTags();
        assertThat(tags).isNotNull().anyMatch(TAG_COMMANDER::equals);
    }

    @Test
    void should_provide_responses_on_get_operation_at_rover_state_endpoint() {
        var roverStateGetOperationResponses =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getGet()
                        .getResponses();

        assertThat(roverStateGetOperationResponses).isNotNull();
    }

    @Test
    void should_have_ok_response_on_get_operation_at_rover_state_endpoint() {
        check_response_on_get_operation_at_rover_state_endpoint(
                RESPONSE_OK_CODE,
                JSON_MEDIA_TYPE,
                ROVER_STATE_SUCCESS_RESPONSE_SCHEMA_PATH,
                RESPONSE_OK_DESCRIPTION);
    }

    @Test
    void should_have_unauthorized_response_on_get_operation_at_rover_state_endpoint() {
        check_response_on_get_operation_at_rover_state_endpoint(
                RESPONSE_UNAUTHORIZED_CODE,
                JSON_PROBLEM_MEDIA_TYPE,
                ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH,
                RESPONSE_UNAUTHORIZED_DESCRIPTION);
    }

    @Test
    void should_have_too_many_requests_response_on_get_operation_at_rover_state_endpoint() {
        check_response_on_get_operation_at_rover_state_endpoint(
                RESPONSE_TOO_MANY_REQUESTS_CODE,
                JSON_PROBLEM_MEDIA_TYPE,
                ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH,
                RESPONSE_TOO_MANY_REQUESTS_DESCRIPTION);
    }

    @Test
    void should_have_internal_server_error_response_on_get_operation_at_rover_state_endpoint() {
        check_response_on_get_operation_at_rover_state_endpoint(
                RESPONSE_INTERNAL_SERVER_ERROR_CODE,
                JSON_PROBLEM_MEDIA_TYPE,
                ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH,
                RESPONSE_INTERNAL_SERVER_ERROR_DESCRIPTION);
    }

    private void check_response_on_get_operation_at_rover_state_endpoint(
            final String responseKey,
            final String responseContentType,
            final String responseContentSchemaPath,
            final String responseDescription) {
        var roverStateGetOperation =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getGet();
        check_response_on_operation_at_rover_state_endpoint(
                roverStateGetOperation, responseKey, responseContentType,
                responseContentSchemaPath, responseDescription);
    }

    @Test
    void should_have_post_operation_on_rover_state_endpoint_tagged_for_commanders() {
        var roverStatePostOperation =
                getGeneratedApiContract().getPaths().get(ROVER_STATE_PATH).getPost();

        assertThat(roverStatePostOperation).isNotNull();

        assertThat(roverStatePostOperation.getDescription())
                .isEqualTo(ROVER_STATE_UPDATE_OPERATION_DESCRIPTION);

        assertThat(roverStatePostOperation.getOperationId())
                .isEqualTo(ROVER_STATE_UPDATE_OPERATION_ID);

        assertThat(roverStatePostOperation.getSummary())
                .isEqualTo(ROVER_STATE_UPDATE_OPERATION_SUMMARY);

        var tags = roverStatePostOperation.getTags();
        assertThat(tags).isNotNull().anyMatch(TAG_COMMANDER::equals);
    }

    @Test
    void should_provide_request_body_on_post_operation_at_rover_state_endpoint() {
        var roverStatePostOperationRequestBody =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getPost()
                        .getRequestBody();

        assertThat(roverStatePostOperationRequestBody).isNotNull();

        assertThat(roverStatePostOperationRequestBody.getDescription())
                .isEqualTo(ROVER_STATE_UPDATE_REQUEST_BODY_DESCRIPTION);

        assertThat(roverStatePostOperationRequestBody.getRequired()).isTrue();

        var requestContent = roverStatePostOperationRequestBody.getContent();
        assertThat(requestContent).isNotNull();

        var requestContentMediaType = requestContent.get(JSON_MEDIA_TYPE);
        assertThat(requestContentMediaType).isNotNull();

        var requestJsonContentSchema = requestContentMediaType.getSchema();
        assertThat(requestJsonContentSchema).isNotNull();
        assertThat(requestJsonContentSchema.get$ref())
                .isEqualTo(SCHEMAS_STANDARD_PATH + ROVER_STATE_SCHEMA_NAME);
    }

    @Test
    void should_provide_responses_on_post_operation_at_rover_state_endpoint() {
        var roverStatePostOperationResponses =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getGet()
                        .getResponses();

        assertThat(roverStatePostOperationResponses).isNotNull();
    }

    @Test
    void should_have_ok_response_on_post_operation_at_rover_state_endpoint() {
        check_response_on_post_operation_at_rover_state_endpoint(
                RESPONSE_ACCEPTED_CODE,
                JSON_MEDIA_TYPE,
                ROVER_STATE_SUCCESS_RESPONSE_SCHEMA_PATH,
                RESPONSE_ACCEPTED_DESCRIPTION);
    }

    @Test
    void should_have_bad_request_response_on_post_operation_at_rover_state_endpoint() {
        check_response_on_post_operation_at_rover_state_endpoint(
                RESPONSE_BAD_REQUEST_CODE,
                JSON_PROBLEM_MEDIA_TYPE,
                ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH,
                RESPONSE_BAD_REQUEST_DESCRIPTION);
    }

    @Test
    void should_have_unauthorized_response_on_post_operation_at_rover_state_endpoint() {
        check_response_on_post_operation_at_rover_state_endpoint(
                RESPONSE_UNAUTHORIZED_CODE,
                JSON_PROBLEM_MEDIA_TYPE,
                ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH,
                RESPONSE_UNAUTHORIZED_DESCRIPTION);
    }

    @Test
    void should_have_too_many_requests_response_on_post_operation_at_rover_state_endpoint() {
        check_response_on_post_operation_at_rover_state_endpoint(
                RESPONSE_TOO_MANY_REQUESTS_CODE,
                JSON_PROBLEM_MEDIA_TYPE,
                ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH,
                RESPONSE_TOO_MANY_REQUESTS_DESCRIPTION);
    }

    @Test
    void should_have_internal_server_error_response_on_post_operation_at_rover_state_endpoint() {
        check_response_on_post_operation_at_rover_state_endpoint(
                RESPONSE_INTERNAL_SERVER_ERROR_CODE,
                JSON_PROBLEM_MEDIA_TYPE,
                ROVER_STATE_ERROR_RESPONSE_SCHEMA_PATH,
                RESPONSE_INTERNAL_SERVER_ERROR_DESCRIPTION);
    }

    private void check_response_on_post_operation_at_rover_state_endpoint(
            final String responseKey,
            final String responseContentType,
            final String responseContentSchemaPath,
            final String responseDescription) {
        var roverStatePostOperation =
                getGeneratedApiContract()
                        .getPaths()
                        .get(ROVER_STATE_PATH)
                        .getPost();
        check_response_on_operation_at_rover_state_endpoint(
                roverStatePostOperation, responseKey, responseContentType,
                responseContentSchemaPath, responseDescription);
    }

    private void check_response_on_operation_at_rover_state_endpoint(
            final Operation operation,
            final String responseKey,
            final String responseContentType,
            final String responseContentSchemaPath,
            final String responseDescription) {
        var response = operation.getResponses().get(responseKey);
        assertThat(response).isNotNull();
        assertThat(response.getDescription()).isEqualTo(responseDescription);

        var content = response.getContent();
        assertThat(content).isNotNull();

        var jsonContent = content.get(responseContentType);
        assertThat(jsonContent).isNotNull();

        var jsonContentSchema = jsonContent.getSchema();
        assertThat(jsonContentSchema).isNotNull();
        assertThat(jsonContentSchema.get$ref()).isEqualTo(responseContentSchemaPath);
    }
}
