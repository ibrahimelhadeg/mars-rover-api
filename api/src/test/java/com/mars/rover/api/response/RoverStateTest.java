package com.mars.rover.api.response;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mars.rover.core.Direction;

import static java.lang.reflect.Modifier.isPublic;

import static org.assertj.core.api.Assertions.assertThat;

class RoverStateTest {

    private RoverState emptyRoverState1;
    private RoverState emptyRoverState2;
    private RoverState roverState1;
    private RoverState roverState2;

    @BeforeEach
    void prepare() {

        emptyRoverState1 = RoverState.builder().build();
        emptyRoverState2 = RoverState.builder().build();

        roverState1 = RoverState.builder()
                .withDirection(Direction.EST)
                .withLocation(
                        RoverState.Location
                                .builder()
                                .withXCoordinate(1)
                                .withYCoordinate(-1)
                                .build())
                .build();
        roverState2 = RoverState.builder()
                .withDirection(Direction.EST)
                .withLocation(
                        RoverState.Location
                                .builder()
                                .withXCoordinate(1)
                                .withYCoordinate(-1)
                                .build())
                .build();
    }

    @Test
    void both_empty_roverState_instances_should_be_equal() {
        assertThat(emptyRoverState1.equals(emptyRoverState2)).isTrue();
    }

    @Test
    void should_be_equal() {
        assertThat(roverState1.equals(roverState2)).isTrue();
    }

    @Test
    void should_not_be_equal() {
        assertThat(emptyRoverState1.equals(roverState2)).isFalse();
    }

    @Test
    void both_empty_roverState_instances_should_have_the_same_hashCode() {
        assertThat(emptyRoverState1.hashCode())
                .isEqualTo(emptyRoverState2.hashCode());
    }

    @Test
    void both_non_empty_roverState_instances_should_have_the_same_hashCode() {
        assertThat(roverState1.hashCode())
                .isEqualTo(roverState2.hashCode());
    }

    @Test
    void should_not_have_the_same_hashCode() {
        assertThat(emptyRoverState1.hashCode())
                .isNotEqualTo(roverState2.hashCode());
    }

    @Test
    void both_empty_roverState_instances_should_have_the_same_toString() {
        assertThat(emptyRoverState1.toString())
                .isEqualTo(emptyRoverState2.toString());
    }

    @Test
    void both_non_empty_roverState_instances_should_have_the_same_toString() {
        assertThat(roverState1.toString())
                .isEqualTo(roverState2.toString());
    }

    @Test
    void should_not_have_the_same_toString() {
        assertThat(emptyRoverState1.toString())
                .isNotEqualTo(roverState2.toString());
    }

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
