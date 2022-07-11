package com.mars.rover.api.impl.dao;

import java.time.Instant;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.mars.rover.api.impl.model.RoverStateEntity;

public interface RoverStateRepository extends CrudRepository<RoverStateEntity, Instant> {

    Optional<RoverStateEntity> findTopByOrderByUpdateTimestampDesc();
}
