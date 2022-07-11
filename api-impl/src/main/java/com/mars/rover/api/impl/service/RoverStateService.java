package com.mars.rover.api.impl.service;

import com.mars.rover.api.response.RoverState;

public interface RoverStateService {

    RoverState getMostRecentRoverState();

    RoverState updateRoverState(RoverState roverState);
}
