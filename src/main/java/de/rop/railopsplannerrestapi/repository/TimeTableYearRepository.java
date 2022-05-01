package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeTableYearRepository extends JpaRepository<TimeTableYear, Long> {
}
