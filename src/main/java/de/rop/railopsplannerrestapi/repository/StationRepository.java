package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StationRepository extends JpaRepository<Station, String> {
}
