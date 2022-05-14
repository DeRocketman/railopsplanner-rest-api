package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.TrainFailure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrainFailureRepository extends JpaRepository<TrainFailure, Long> {
}
