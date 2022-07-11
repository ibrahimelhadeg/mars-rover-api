package com.mars.rover.api.impl;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.map.repository.config.EnableMapRepositories;

@SpringBootApplication
@EnableMapRepositories
public class MarsRoverApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarsRoverApplication.class, args);
    }
}
