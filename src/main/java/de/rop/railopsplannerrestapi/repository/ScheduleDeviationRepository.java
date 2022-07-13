package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.ScheduleDeviation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduleDeviationRepository extends JpaRepository<ScheduleDeviation, String> {
}
