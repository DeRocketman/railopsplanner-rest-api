package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.RailNetwork;
import de.rop.railopsplannerrestapi.repository.RailNetworkRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rail-network")
public class RailNetworkController {
    private final RailNetworkRepository railNetworkRepository;

    public RailNetworkController(RailNetworkRepository railNetworkRepository) {
        this.railNetworkRepository = railNetworkRepository;
    }

    @GetMapping("")
    public List<RailNetwork> index() {
        return railNetworkRepository.findAll();
    }

    @PostMapping("/create")
    public RailNetwork newRailNetwork(@RequestBody RailNetwork newRailNetwork) {
        return railNetworkRepository.save(newRailNetwork);
    }
}
