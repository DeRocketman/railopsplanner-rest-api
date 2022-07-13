package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.RailNetwork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RailNetworkRepository extends JpaRepository<RailNetwork, String> {

}
