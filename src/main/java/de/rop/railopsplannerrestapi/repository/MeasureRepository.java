package de.rop.railopsplannerrestapi.repository;


import de.rop.railopsplannerrestapi.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasureRepository extends JpaRepository<Measure, Long> {
}
