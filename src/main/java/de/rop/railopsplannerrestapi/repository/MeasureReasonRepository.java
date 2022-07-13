package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.MeasureReason;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MeasureReasonRepository extends JpaRepository<MeasureReason, String> {

}
