package de.rop.railopsplannerrestapi.controller;


import de.rop.railopsplannerrestapi.entity.Station;
import de.rop.railopsplannerrestapi.repository.StationRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/station")
public class StationController {
    private final StationRepository stationRepository;


    public StationController(StationRepository stationRepository) {
        this.stationRepository = stationRepository;
    }

    @GetMapping("")
    public List<Station> index() {
        return stationRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Station newStation(@RequestBody Station newStation) {
        return stationRepository.save(newStation);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Station> updateToDoItem(@PathVariable("id") String id, @RequestBody Station station) {
        Optional<Station> storedStation = stationRepository.findById(id);

        if (storedStation.isPresent()) {
            return new ResponseEntity<>(stationRepository.save(station), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Station> show(@PathVariable("id") String id) {
        Optional<Station> selectedStation = this.stationRepository.findById(id);
        if (selectedStation.isPresent()) {
            return new ResponseEntity<>(selectedStation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteStation(@PathVariable String id) {
        try {
            this.stationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
