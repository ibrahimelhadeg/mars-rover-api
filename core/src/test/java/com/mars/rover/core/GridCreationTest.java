package com.mars.rover.core;

import java.util.Set;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.jupiter.api.Assertions.assertThrows;

class GridCreationTest {

    @Test
    void should_create_grid() {
        var obstacleAtCenter =
                Location.builder().withX(0).withY(0).build();
        var obstacleAtTopLeft =
                Location.builder().withX(-5).withY(5).build();
        var obstacleAtTopRight =
                Location.builder().withX(5).withY(5).build();
        var obstacleAtBottomRight =
                Location.builder().withX(5).withY(-5).build();
        var obstacleAtBottomLeft =
                Location.builder().withX(-5).withY(-5).build();

        var obstacles = Set.of(
                obstacleAtCenter,
                obstacleAtTopLeft,
                obstacleAtTopRight,
                obstacleAtBottomRight,
                obstacleAtBottomLeft);

        var grid = Grid.builder()
                .withObstacles(obstacles)
                .build();

        assertThat(grid).isNotNull();
        assertThat(grid.obstacles()).isEqualTo(obstacles);
        assertThat(grid.obstacles()).contains(obstacleAtCenter);
        assertThat(grid.obstacles()).contains(obstacleAtTopLeft);
        assertThat(grid.obstacles()).contains(obstacleAtTopRight);
        assertThat(grid.obstacles()).contains(obstacleAtBottomRight);
        assertThat(grid.obstacles()).contains(obstacleAtBottomLeft);
    }

    @Test
    void should_not_create_grid_with_null_obstacles_set() {
        var gridBuilder = Grid.builder();

        var thrown = assertThrows(
                Exception.class,
                () -> gridBuilder.withObstacles(null),
                "An Exception was expected");

        assertThat(thrown.getMessage())
                .isEqualTo("obstacles is marked non-null but is null");
    }
}
