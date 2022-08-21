package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.CrossStation;
import de.rop.railopsplannerrestapi.entity.StartEndStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrossStationRepository extends JpaRepository<CrossStation, String> {
}
