package com.mars.rover.api;

import io.swagger.v3.oas.models.media.Schema;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import com.mars.rover.api.dto.RoverState;

import static io.swagger.v3.parser.util.SchemaTypeUtil.*;

import static org.assertj.core.api.Assertions.assertThat;

import static com.mars.rover.api.GeneratedApiContractExtension.getGeneratedApiContract;
import static com.mars.rover.api.dto.RoverState.Location.LOCATION_SCHEMA_NAME;

@ExtendWith(GeneratedApiContractExtension.class)
class ShouldProvideLocationSchemaTest {

    public static Schema<?> LOCATION_SCHEMA;

    @BeforeAll
    static void retrieve_location_schema() {
        LOCATION_SCHEMA = getGeneratedApiContract()
                .getComponents()
                .getSchemas()
                .get(LOCATION_SCHEMA_NAME);
    }


    @Test
    void should_provide_location_schema_of_type_object() {
        assertThat(LOCATION_SCHEMA.getType()).isEqualTo(OBJECT_TYPE);
    }

    @Test
    void location_schema_should_have_a_required_property_named_x() {
        var xCoordinateProperty =
                LOCATION_SCHEMA.getProperties()
                        .get(RoverState.Location.Fields.X_COORDINATE);

        assertThat(xCoordinateProperty).isNotNull();
        assertThat(xCoordinateProperty.getType()).isEqualTo(INTEGER_TYPE);
        assertThat(xCoordinateProperty.getFormat()).isEqualTo(INTEGER32_FORMAT);
        assertThat(xCoordinateProperty.getExample()).isEqualTo(0);

        assertThat(LOCATION_SCHEMA.getRequired())
                .anyMatch(RoverState.Location.Fields.X_COORDINATE::equals);
    }

    @Test
    void location_schema_should_have_a_required_property_named_y() {
        var yCoordinateProperty =
                LOCATION_SCHEMA.getProperties()
                        .get(RoverState.Location.Fields.Y_COORDINATE);

        assertThat(yCoordinateProperty).isNotNull();
        assertThat(yCoordinateProperty.getType()).isEqualTo(INTEGER_TYPE);
        assertThat(yCoordinateProperty.getFormat()).isEqualTo(INTEGER32_FORMAT);
        assertThat(yCoordinateProperty.getExample()).isEqualTo(-1);

        assertThat(LOCATION_SCHEMA.getRequired())
                .anyMatch(RoverState.Location.Fields.Y_COORDINATE::equals);
    }
}
