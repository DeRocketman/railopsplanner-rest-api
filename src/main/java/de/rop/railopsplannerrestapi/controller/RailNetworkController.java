package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.RailNetwork;
import de.rop.railopsplannerrestapi.repository.RailNetworkRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    @ResponseStatus(HttpStatus.CREATED)
    public RailNetwork newRailNetwork(@RequestBody RailNetwork newRailNetwork) {
        return railNetworkRepository.save(newRailNetwork);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<RailNetwork> updateRailNetwork(@PathVariable("id") String id, @RequestBody RailNetwork railNetwork) {
        Optional<RailNetwork> storedRailNetwork = railNetworkRepository.findById(id);

        if (storedRailNetwork.isPresent()) {
           RailNetwork tempRailNetwork = storedRailNetwork.get();
           tempRailNetwork.setName(railNetwork.getName());
           tempRailNetwork.setAbbreviation(railNetwork.getAbbreviation());
           return new ResponseEntity<>(railNetworkRepository.save(tempRailNetwork), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<RailNetwork> show(@PathVariable("id") String id) {
        Optional<RailNetwork> selectedRailNetwork = this.railNetworkRepository.findById(id);
        if (selectedRailNetwork.isPresent()) {
            return new ResponseEntity<>(selectedRailNetwork.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteRailNetwork(@PathVariable String id) {
        try {
            this.railNetworkRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
