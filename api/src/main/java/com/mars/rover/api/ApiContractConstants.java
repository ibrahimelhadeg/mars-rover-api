package com.mars.rover.api;

import lombok.experimental.UtilityClass;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.servers.ServerVariable;
import io.swagger.v3.oas.annotations.tags.Tag;

import static com.mars.rover.api.ApiContractConstants.*;

@UtilityClass
@OpenAPIDefinition(
        info = @Info(
                title = API_NAME,
                description = API_DESCRIPTION,
                version = API_VERSION,
                contact = @Contact(email = API_CONTACT),
                license = @License(name = API_LICENCE_NAME, url = API_LICENCE_URL)),
        tags = {
                @Tag(
                        name = TAG_COMMANDER,
                        description = TAG_COMMANDER_DESCRIPTION)
        },
        security = {
                @SecurityRequirement(name = API_SECURITY_SCHEMA_NAME)
        },
        servers = {
                @Server(
                        url = API_SERVER_URL_FORMAT,
                        variables = {
                                @ServerVariable(
                                        name = API_SERVER_ENVIRONMENT_VARIABLE_NAME,
                                        description = API_SERVER_ENVIRONMENT_VARIABLE_DESCRIPTION,
                                        defaultValue = API_SERVER_ENVIRONMENT_VARIABLE_DEFAULT_VALUE,
                                        allowableValues = {
                                                API_SERVER_ENVIRONMENT_VARIABLE_PRODUCTION_VALUE,
                                                API_SERVER_ENVIRONMENT_VARIABLE_DEVELOPMENT_VALUE,
                                                API_SERVER_ENVIRONMENT_VARIABLE_STAGING_VALUE
                                        })
                        })
        })
@SecurityScheme(
        name = API_SECURITY_SCHEMA_NAME,
        scheme = API_SECURITY_MECHANISM,
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER,
        bearerFormat = API_SECURITY_BEARER_FORMAT)
public class ApiContractConstants {

    public static final String API_NAME = "Mars Rover API";
    public static final String API_DESCRIPTION = "An API to command a rover on Mars";
    public static final String API_VERSION = "1.0.0";
    public static final String API_CONTACT = "contact@mars-rover.io";
    public static final String API_LICENCE_NAME = "¯\\_(ツ)_/¯";
    public static final String API_LICENCE_URL = "https://api.mars-rover.io/licence";

    public static final String API_SECURITY_SCHEMA_NAME = "BearerAuth";
    public static final String API_SECURITY_MECHANISM = "bearer";
    public static final String API_SECURITY_BEARER_FORMAT = "JWT";
    public static final String API_SERVER_URL_FORMAT = "https://{environment}.mars-rover.io/v1";
    public static final String API_SERVER_ENVIRONMENT_VARIABLE_NAME = "environment";
    public static final String API_SERVER_ENVIRONMENT_VARIABLE_DESCRIPTION = "the API environment";
    public static final String API_SERVER_ENVIRONMENT_VARIABLE_DEVELOPMENT_VALUE = "development";
    public static final String API_SERVER_ENVIRONMENT_VARIABLE_STAGING_VALUE = "staging";
    public static final String API_SERVER_ENVIRONMENT_VARIABLE_PRODUCTION_VALUE = "api";
    public static final String API_SERVER_ENVIRONMENT_VARIABLE_DEFAULT_VALUE = API_SERVER_ENVIRONMENT_VARIABLE_PRODUCTION_VALUE;

    public static final String PATH_SEPARATOR = "/";
    public static final String API_ROOT_PATH = PATH_SEPARATOR + "api";
    public static final String API_VERSION_PREFIX = "v";
    public static final String API_VERSION_1 = "1";
    public static final String API_VERSION_1_MARKER = API_VERSION_PREFIX + API_VERSION_1;
    public static final String API_VERSION_1_PATH = PATH_SEPARATOR + API_VERSION_1_MARKER;

    public static final String JSON_MEDIA_TYPE = "application/json";
    public static final String JSON_PROBLEM_MEDIA_TYPE = "application/problem+json";

    public static final String TAG_COMMANDER = "commander";
    public static final String TAG_COMMANDER_DESCRIPTION = "Guiding the rover";

    public static final String RESPONSE_ACCEPTED_CODE = "202";
    public static final String RESPONSE_ACCEPTED_DESCRIPTION = "The rover state update has been taken into account";
    public static final String RESPONSE_BAD_REQUEST_CODE = "400";
    public static final String RESPONSE_BAD_REQUEST_DESCRIPTION = "Please review your request";
    public static final String RESPONSE_OK_CODE = "200";
    public static final String RESPONSE_OK_DESCRIPTION = "Most recent rover state";
    public static final String RESPONSE_INTERNAL_SERVER_ERROR_CODE = "500";
    public static final String RESPONSE_INTERNAL_SERVER_ERROR_DESCRIPTION = "Something wrong";
    public static final String RESPONSE_TOO_MANY_REQUESTS_CODE = "429";
    public static final String RESPONSE_TOO_MANY_REQUESTS_DESCRIPTION = "Too many requests";
    public static final String RESPONSE_UNAUTHORIZED_CODE = "401";
    public static final String RESPONSE_UNAUTHORIZED_DESCRIPTION = "Unauthorized request";

    public static final String ROVER_STATE_RETRIEVE_OPERATION_DESCRIPTION = "Retrieve the state of the rover: location and direction";
    public static final String ROVER_STATE_RETRIEVE_OPERATION_ID = "getRoverState";
    public static final String ROVER_STATE_RETRIEVE_OPERATION_SUMMARY = "Retrieve the rover's state";

    public static final String ROVER_STATE_UPDATE_OPERATION_DESCRIPTION = "Update the state of the rover: location, direction, or both";
    public static final String ROVER_STATE_UPDATE_OPERATION_ID = "updateRoverState";
    public static final String ROVER_STATE_UPDATE_OPERATION_SUMMARY = "Update the rover's state";
    public static final String ROVER_STATE_UPDATE_REQUEST_BODY_DESCRIPTION = "Rover's new state";
}
