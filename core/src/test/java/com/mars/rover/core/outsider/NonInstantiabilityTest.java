package com.mars.rover.core.outsider;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.mars.rover.core.Main;
import com.mars.rover.core.Rover;

import static java.lang.reflect.Modifier.isPublic;

import static org.assertj.core.api.Assertions.assertThat;

class NonInstantiabilityTest {

    private static Stream<Arguments> nonInstantiableClasses() {
        return Stream.of(
                Arguments.of(Main.class),
                Arguments.of(Rover.class)
        );
    }

    @ParameterizedTest
    @MethodSource("nonInstantiableClasses")
    void test_for_nonInstantiability(Class<?> clazz) {
        var constructors = clazz.getDeclaredConstructors();
        for (var constructor : constructors) {
            assertThat(isPublic(constructor.getModifiers())).isFalse();
        }
    }
}
