package com.mars.rover.api;

import java.util.List;

import io.swagger.v3.oas.models.media.Schema;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.mars.rover.api.response.RoverState;

import static io.swagger.v3.parser.util.SchemaTypeUtil.OBJECT_TYPE;
import static io.swagger.v3.parser.util.SchemaTypeUtil.STRING_TYPE;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.ApiContractTestUtil.SCHEMAS_STANDARD_PATH;
import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;
import static com.mars.rover.api.response.RoverState.Location.LOCATION_SCHEMA_NAME;
import static com.mars.rover.api.response.RoverState.ROVER_STATE_SCHEMA_NAME;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldProvideRoverStateSchemaTest {

    public static final String DIRECTION_SCHEMA_NAME = "Direction";
    public static final String DIRECTION_SCHEMA_PATH = SCHEMAS_STANDARD_PATH + DIRECTION_SCHEMA_NAME;

    public static Schema<?> ROVER_STATE_SCHEMA;

    @BeforeAll
    static void retrieve_location_schema() {
        ROVER_STATE_SCHEMA = getGeneratedApiContract()
                .getComponents()
                .getSchemas()
                .get(ROVER_STATE_SCHEMA_NAME);
    }

    @Test
    void should_provide_location_schema_of_type_object() {
        assertThat(ROVER_STATE_SCHEMA.getType()).isEqualTo(OBJECT_TYPE);
    }

    @Test
    void rover_state_schema_should_have_a_required_enumerated_property_named_direction() {
        var directionProperty =
                ROVER_STATE_SCHEMA.getProperties().get(RoverState.Fields.DIRECTION);

        assertThat(directionProperty).isNotNull();
        assertThat(directionProperty.get$ref()).isEqualTo(DIRECTION_SCHEMA_PATH);
        assertThat(ROVER_STATE_SCHEMA.getRequired()).anyMatch(RoverState.Fields.DIRECTION::equals);

        var directionSchemaDefinition =
                getGeneratedApiContract()
                        .getComponents()
                        .getSchemas()
                        .get(DIRECTION_SCHEMA_NAME);

        assertThat(directionSchemaDefinition).isNotNull();
        assertThat(directionSchemaDefinition.getType()).isEqualTo(STRING_TYPE);
        assertThat(directionSchemaDefinition.getEnum())
                .isNotNull()
                .hasSize(4)
                .isEqualTo(List.of("Direction.EST", "Direction.NORTH", "Direction.SOUTH", "Direction.WEST"));
    }

    @Test
    void rover_state_schema_should_have_a_required_property_named_location() {
        var locationProperty =
                ROVER_STATE_SCHEMA.getProperties().get(RoverState.Fields.LOCATION);

        assertThat(locationProperty).isNotNull();
        assertThat(locationProperty.get$ref())
                .isEqualTo(SCHEMAS_STANDARD_PATH + LOCATION_SCHEMA_NAME);
        assertThat(ROVER_STATE_SCHEMA.getRequired()).anyMatch(RoverState.Fields.LOCATION::equals);

        var locationSchemaDefinition =
                getGeneratedApiContract()
                        .getComponents()
                        .getSchemas()
                        .get(LOCATION_SCHEMA_NAME);

        assertThat(locationSchemaDefinition).isNotNull();
        assertThat(locationSchemaDefinition.getType()).isEqualTo(OBJECT_TYPE);
        assertThat(locationSchemaDefinition.getRequired())
                .isNotNull()
                .hasSize(2)
                .anyMatch(RoverState.Location.Fields.X_COORDINATE::equals)
                .anyMatch(RoverState.Location.Fields.Y_COORDINATE::equals);
    }
}
