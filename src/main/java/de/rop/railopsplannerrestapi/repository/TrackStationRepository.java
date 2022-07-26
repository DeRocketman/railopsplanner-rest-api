package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.TrackStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackStationRepository extends JpaRepository<TrackStation, String> {
}
