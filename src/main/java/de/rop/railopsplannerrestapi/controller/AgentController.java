package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.TrackStation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import de.rop.railopsplannerrestapi.entity.Agent;
import de.rop.railopsplannerrestapi.repository.AgentRepository;

import java.util.List;
import java.util.Optional;

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

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Agent newAgent(@RequestBody Agent newAgent) {
        return agentRepository.save(newAgent);
    }
}
