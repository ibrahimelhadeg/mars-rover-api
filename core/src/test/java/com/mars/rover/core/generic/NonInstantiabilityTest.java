package com.mars.rover.core.generic;

import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import com.mars.rover.core.Rover;

import static java.lang.reflect.Modifier.isPublic;

import static org.assertj.core.api.Assertions.assertThat;

class NonInstantiabilityTest {

    @ParameterizedTest
    @MethodSource("nonInstantiableClasses")
    void test_for_nonInstantiability(TestCase testCase) {
        var constructors = testCase.clazz().getDeclaredConstructors();
        for (var constructor : constructors) {
            assertThat(isPublic(constructor.getModifiers())).isFalse();
        }
    }

    private static Stream<TestCase> nonInstantiableClasses() {
        return Stream.of(
                new TestCase(Rover.class)
        );
    }

    private record TestCase(Class<?> clazz) {
    }
}
