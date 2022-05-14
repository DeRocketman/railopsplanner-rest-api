package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.PlanningPeriod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanningPeriodRepository extends JpaRepository<PlanningPeriod, Long> {
}
