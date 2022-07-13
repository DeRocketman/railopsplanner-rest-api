package de.rop.railopsplannerrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rop.railopsplannerrestapi.entity.Agent;
import org.springframework.stereotype.Repository;

@Repository
public interface AgentRepository extends JpaRepository<Agent, String> {
}
