package com.mars.rover.api.impl.service;

import java.time.Instant;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.mars.rover.api.impl.dao.RoverStateRepository;
import com.mars.rover.api.impl.model.RoverStateEntity;
import com.mars.rover.api.impl.model.exception.UnavailableRoverStateProblem;
import com.mars.rover.api.response.RoverState;
import com.mars.rover.core.Location;

@Service
@AllArgsConstructor
public class RoverStateServiceImpl implements RoverStateService {

    private final RoverStateRepository roverStateRepository;

    @Override
    public RoverState getMostRecentRoverState() {
        return roverStateRepository
                .findTopByOrderByUpdateTimestampDesc()
                .map(roverStateEntity ->
                        RoverState.builder()
                                .withDirection(roverStateEntity.direction())
                                .withLocation(
                                        RoverState.Location.builder()
                                                .withXCoordinate(roverStateEntity.location().x())
                                                .withYCoordinate(roverStateEntity.location().y())
                                                .build())
                                .build())
                .orElseThrow(() -> new UnavailableRoverStateProblem("Canot retrieve the rover's state"));
    }

    @Override
    public RoverState updateRoverState(final RoverState roverState) {
        var roverStateEntityToSave = RoverStateEntity.builder()
                .withUpdateTimestamp(Instant.now())
                .withDirection(roverState.direction())
                .withLocation(
                        Location.builder()
                                .withX(roverState.location().xCoordinate())
                                .withY(roverState.location().yCoordinate())
                                .build())
                .build();

        var savedRoverStateEntity = roverStateRepository.save(roverStateEntityToSave);
        return RoverState.builder()
                .withDirection(savedRoverStateEntity.direction())
                .withLocation(
                        RoverState.Location.builder()
                                .withXCoordinate(savedRoverStateEntity.location().x())
                                .withYCoordinate(savedRoverStateEntity.location().y())
                                .build())
                .build();
    }
}
