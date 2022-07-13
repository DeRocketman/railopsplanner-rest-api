package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.PlanningPeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanningPeriodRepository extends JpaRepository<PlanningPeriod, String> {
}
