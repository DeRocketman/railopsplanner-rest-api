package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.ScheduleDeviation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleDeviationRepository extends JpaRepository<ScheduleDeviation, Long> {
}
