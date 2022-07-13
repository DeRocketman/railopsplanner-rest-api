package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.TrainFailure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainFailureRepository extends JpaRepository<TrainFailure, String> {
}
