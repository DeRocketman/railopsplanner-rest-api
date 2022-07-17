package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.TrackGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrackGroupRepository extends JpaRepository<TrackGroup, String> {
}
