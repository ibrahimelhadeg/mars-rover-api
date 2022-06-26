package com.mars.rover.core;

import java.lang.reflect.InvocationTargetException;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.fail;

abstract class LocationAbstractTest {

    protected void should_adjust_location(String testedMethodName,
                                          int givenX,
                                          int givenY,
                                          int expectedX,
                                          int expectedY) {
        var startLocation =
                Location.builder()
                        .withX(givenX)
                        .withY(givenY)
                        .build();
        try {
            var testedMethod = Location.class.getMethod(testedMethodName);
            var resultedLocation = (Location) testedMethod.invoke(startLocation);

            assertThat(resultedLocation).isNotNull();
            assertThat(resultedLocation.x()).isEqualTo(expectedX);
            assertThat(resultedLocation.y()).isEqualTo(expectedY);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException exception) {
            fail("Exception " + exception);
        }
    }
}
