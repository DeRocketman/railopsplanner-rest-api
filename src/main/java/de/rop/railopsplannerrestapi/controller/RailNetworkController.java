package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.RailNetwork;
import de.rop.railopsplannerrestapi.repository.RailNetworkRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/rail_network")
public class RailNetworkController {
    private RailNetworkRepository railNetworkRepository;

    public RailNetworkController(RailNetworkRepository railNetworkRepository) {
        this.railNetworkRepository = railNetworkRepository;
    }

    @GetMapping("")
    public List<RailNetwork> index() {
        return railNetworkRepository.findAll();
    }


}
