package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeTableYearRepository extends JpaRepository<TimeTableYear, String> {
}
