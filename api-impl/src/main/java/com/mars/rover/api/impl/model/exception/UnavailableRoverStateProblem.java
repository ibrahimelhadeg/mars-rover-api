package com.mars.rover.api.impl.model.exception;

import java.net.URI;

import org.zalando.problem.AbstractThrowableProblem;

import static org.zalando.problem.Status.SERVICE_UNAVAILABLE;

public class UnavailableRoverStateProblem extends AbstractThrowableProblem {

    private static final URI TYPE = URI.create("https://mars-rover.io/unavailable");

    public UnavailableRoverStateProblem(final String message) {
        super(
                TYPE,
                message,
                SERVICE_UNAVAILABLE);
    }
}
