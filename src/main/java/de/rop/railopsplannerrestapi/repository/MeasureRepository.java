package de.rop.railopsplannerrestapi.repository;


import de.rop.railopsplannerrestapi.entity.Measure;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureRepository extends JpaRepository<Measure, String> {
}
