package de.rop.railopsplannerrestapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import de.rop.railopsplannerrestapi.entity.Agent;
import de.rop.railopsplannerrestapi.repository.AgentRepository;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api/agent")
public class AgentController {
    private final AgentRepository agentRepository;

    public AgentController(AgentRepository agentRepository) {
        this.agentRepository = agentRepository;
    }

    @GetMapping("")
    public List<Agent> index() {
        return agentRepository.findAll();
    }
}
