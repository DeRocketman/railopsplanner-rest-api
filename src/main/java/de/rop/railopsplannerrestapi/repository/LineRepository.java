package de.rop.railopsplannerrestapi.repository;

import de.rop.railopsplannerrestapi.entity.Line;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineRepository extends JpaRepository<Line, String> {

}
