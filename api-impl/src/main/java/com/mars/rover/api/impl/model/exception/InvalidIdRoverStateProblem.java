package com.mars.rover.api.impl.model.exception;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;

import com.mars.rover.api.impl.model.RoverStateEntity;

import static org.zalando.problem.Status.BAD_REQUEST;

public class InvalidIdRoverStateProblem extends AbstractThrowableProblem {

    private static final URI TYPE = URI.create("https://mars-rover.io/bad-request");

    public InvalidIdRoverStateProblem(final String message,
                                      final RoverStateEntity invalidRoverStateEntity) {
        super(
                TYPE,
                message,
                BAD_REQUEST,
                invalidRoverStateEntity == null ? "null" : invalidRoverStateEntity.toString());
    }
}
