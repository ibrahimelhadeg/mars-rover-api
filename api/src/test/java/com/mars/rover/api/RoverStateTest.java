package com.mars.rover.api;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mars.rover.api.dto.RoverState;
import com.mars.rover.core.Direction;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

import static java.lang.reflect.Modifier.isPublic;

import static org.assertj.core.api.Assertions.assertThat;

class RoverStateTest {

    @Test
    void should_create_rover_state_with_all_required_fields() {
        int xCoordinate = 1, yCoordinate = 1;
        var location =
                RoverState.Location.builder()
                        .withXCoordinate(xCoordinate)
                        .withYCoordinate(yCoordinate)
                        .build();

        assertThat(location).isNotNull();
        assertThat(location.xCoordinate()).isEqualTo(xCoordinate);
        assertThat(location.yCoordinate()).isEqualTo(yCoordinate);

        var direction = Direction.EST;
        var roverState = RoverState.builder()
                .withDirection(direction)
                .withLocation(location)
                .build();

        assertThat(roverState).isNotNull();
        assertThat(roverState.direction()).isEqualTo(direction);
        assertThat(roverState.location()).isEqualTo(location);
    }

    @Test
    void test_equals_contract() {
        EqualsVerifier
                .forClasses(RoverState.class, RoverState.Location.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }

    @ParameterizedTest
    @MethodSource("nonInstantiableClasses")
    void test_for_nonInstantiability(NonInstantiabilityTestCase testCase) {
        var constructors = testCase.clazz().getDeclaredConstructors();
        for (var constructor : constructors) {
            assertThat(isPublic(constructor.getModifiers())).isFalse();
        }
    }

    private static Stream<NonInstantiabilityTestCase> nonInstantiableClasses() {
        return Stream.of(
                new NonInstantiabilityTestCase(RoverState.class),
                new NonInstantiabilityTestCase(RoverState.Location.class)
        );
    }

    private record NonInstantiabilityTestCase(Class<?> clazz) {
    }
}
