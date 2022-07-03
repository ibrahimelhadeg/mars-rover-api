package com.mars.rover.api;

import java.util.List;

import io.swagger.v3.oas.models.media.Schema;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.mars.rover.api.dto.RoverState;

import static io.swagger.v3.parser.util.SchemaTypeUtil.OBJECT_TYPE;
import static io.swagger.v3.parser.util.SchemaTypeUtil.STRING_TYPE;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.ApiContractTestUtil.SCHEMAS_STANDARD_PATH;
import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;
import static com.mars.rover.api.dto.RoverState.Location.LOCATION_SCHEMA_NAME;
import static com.mars.rover.api.dto.RoverState.ROVER_STATE_SCHEMA_NAME;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldProvideRoverStateSchemaTest {

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
        assertThat(directionProperty.getType()).isEqualTo(STRING_TYPE);
        assertThat(directionProperty.getEnum())
                .isEqualTo(List.of("EST", "NORTH", "SOUTH", "WEST"));

        assertThat(ROVER_STATE_SCHEMA.getRequired()).anyMatch(RoverState.Fields.DIRECTION::equals);
    }

    @Test
    void rover_state_schema_should_have_a_required_property_named_location() {
        var locationProperty =
                ROVER_STATE_SCHEMA.getProperties().get(RoverState.Fields.LOCATION);

        assertThat(locationProperty).isNotNull();
        assertThat(locationProperty.get$ref())
                .isEqualTo(SCHEMAS_STANDARD_PATH + LOCATION_SCHEMA_NAME);

        assertThat(ROVER_STATE_SCHEMA.getRequired()).anyMatch(RoverState.Fields.LOCATION::equals);
    }
}
