package com.mars.rover.core;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GridReportingObstacleTest {

    private Grid testedGrid;

    @BeforeEach
    void foo() {
        var obstacle =
                Location.builder().withX(0).withY(1).build();
        testedGrid = Grid.builder()
                .withObstacles(Set.of(obstacle))
                .build();
    }

    @Test
    void should_report_obstacle() {
        var location =
                Location.builder()
                        .withX(0)
                        .withY(1)
                        .build();

        var isLocationFree = testedGrid.isFree(location);

        assertThat(isLocationFree).isFalse();
    }

    @Test
    void should_not_report_obstacle() {
        var location =
                Location.builder()
                        .withX(0)
                        .withY(0)
                        .build();

        var isLocationFree = testedGrid.isFree(location);

        assertThat(isLocationFree).isTrue();
    }
}
