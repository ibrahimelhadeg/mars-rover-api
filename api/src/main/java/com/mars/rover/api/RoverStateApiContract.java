package com.mars.rover.api;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import com.mars.rover.api.response.RoverState;
import com.mars.rover.api.response.RoverStateErrorResponse;
import com.mars.rover.api.response.RoverStateResponse;
import com.mars.rover.api.response.RoverStateSuccessResponse;

import static com.mars.rover.api.ApiContractConstants.*;
import static com.mars.rover.api.RoverStateApiContract.ROVER_STATE_PATH;
import static com.mars.rover.api.response.RoverState.ROVER_STATE_SCHEMA_NAME;

@Path(ROVER_STATE_PATH)
@Produces({JSON_MEDIA_TYPE, JSON_PROBLEM_MEDIA_TYPE})
public interface RoverStateApiContract {

    String ROVER_STATE_RESOURCE_NAME = ROVER_STATE_SCHEMA_NAME + "s";
    String ROVER_STATE_PATH = PATH_SEPARATOR + ROVER_STATE_RESOURCE_NAME;

    @GET
    @Operation(
            tags = TAG_COMMANDER,
            summary = ROVER_STATE_RETRIEVE_OPERATION_SUMMARY,
            operationId = ROVER_STATE_RETRIEVE_OPERATION_ID,
            description = ROVER_STATE_RETRIEVE_OPERATION_DESCRIPTION,
            security = {@SecurityRequirement(name = API_SECURITY_SCHEMA_NAME)},
            responses = {
                    @ApiResponse(
                            responseCode = RESPONSE_OK_CODE,
                            description = RESPONSE_OK_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateSuccessResponse.class))}),
                    @ApiResponse(
                            responseCode = RESPONSE_UNAUTHORIZED_CODE,
                            description = RESPONSE_UNAUTHORIZED_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_PROBLEM_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateErrorResponse.class))}),
                    @ApiResponse(
                            responseCode = RESPONSE_TOO_MANY_REQUESTS_CODE,
                            description = RESPONSE_TOO_MANY_REQUESTS_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_PROBLEM_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateErrorResponse.class))}),
                    @ApiResponse(
                            responseCode = RESPONSE_INTERNAL_SERVER_ERROR_CODE,
                            description = RESPONSE_INTERNAL_SERVER_ERROR_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_PROBLEM_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateErrorResponse.class))})
            })
    RoverStateResponse getRoverState();

    @POST
    @Operation(
            tags = TAG_COMMANDER,
            summary = ROVER_STATE_UPDATE_OPERATION_SUMMARY,
            operationId = ROVER_STATE_UPDATE_OPERATION_ID,
            description = ROVER_STATE_UPDATE_OPERATION_DESCRIPTION,
            security = {@SecurityRequirement(name = API_SECURITY_SCHEMA_NAME)},
            responses = {
                    @ApiResponse(
                            responseCode = RESPONSE_ACCEPTED_CODE,
                            description = RESPONSE_ACCEPTED_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateSuccessResponse.class))}),
                    @ApiResponse(
                            responseCode = RESPONSE_BAD_REQUEST_CODE,
                            description = RESPONSE_BAD_REQUEST_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_PROBLEM_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateErrorResponse.class))}),
                    @ApiResponse(
                            responseCode = RESPONSE_UNAUTHORIZED_CODE,
                            description = RESPONSE_UNAUTHORIZED_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_PROBLEM_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateErrorResponse.class))}),
                    @ApiResponse(
                            responseCode = RESPONSE_TOO_MANY_REQUESTS_CODE,
                            description = RESPONSE_TOO_MANY_REQUESTS_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_PROBLEM_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateErrorResponse.class))}),
                    @ApiResponse(
                            responseCode = RESPONSE_INTERNAL_SERVER_ERROR_CODE,
                            description = RESPONSE_INTERNAL_SERVER_ERROR_DESCRIPTION,
                            content = {@Content(
                                    mediaType = JSON_PROBLEM_MEDIA_TYPE,
                                    schema = @Schema(implementation = RoverStateErrorResponse.class))})
            })
    RoverStateResponse updateRoverState(
            @RequestBody(
                    required = true,
                    description = ROVER_STATE_UPDATE_REQUEST_BODY_DESCRIPTION,
                    content = @Content(
                            mediaType = JSON_MEDIA_TYPE,
                            schema = @Schema(implementation = RoverState.class))) RoverState roverState);
}
