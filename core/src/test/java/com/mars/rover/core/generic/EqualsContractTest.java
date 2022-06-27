package com.mars.rover.core.generic;

import org.junit.jupiter.api.Test;

import com.mars.rover.core.Grid;
import com.mars.rover.core.Location;
import com.mars.rover.core.Rover;
import nl.jqno.equalsverifier.EqualsVerifier;
import nl.jqno.equalsverifier.Warning;

class EqualsContractTest {

    @Test
    void test_equals_contract() {
        EqualsVerifier
                .forClasses(Grid.class, Location.class, Rover.class)
                .suppress(Warning.STRICT_INHERITANCE)
                .verify();
    }
}
