package de.rop.railopsplannerrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import de.rop.railopsplannerrestapi.entity.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
}
