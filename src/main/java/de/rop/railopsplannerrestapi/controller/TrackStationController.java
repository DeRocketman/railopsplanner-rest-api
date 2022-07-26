package de.rop.railopsplannerrestapi.controller;


import de.rop.railopsplannerrestapi.entity.TrackStation;
import de.rop.railopsplannerrestapi.entity.TrackStation;
import de.rop.railopsplannerrestapi.repository.TrackStationRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/track-station")
public class TrackStationController {
    private final TrackStationRepository trackStationRepository;

    public TrackStationController(TrackStationRepository trackStationRepository) {
        this.trackStationRepository = trackStationRepository;
    }

    @GetMapping("")
    public List<TrackStation> index() {
        return trackStationRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackStation newStation(@RequestBody TrackStation newTrackStation) {
        return trackStationRepository.save(newTrackStation);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TrackStation> updateToDoItem(@PathVariable("id") String id, @RequestBody TrackStation trackStation) {
        Optional<TrackStation> storedTrackStation = trackStationRepository.findById(id);

        if (storedTrackStation.isPresent()) {
            return new ResponseEntity<>(trackStationRepository.save(trackStation), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackStation> show(@PathVariable("id") String id) {
        Optional<TrackStation> selectedTrackStation = this.trackStationRepository.findById(id);
        if (selectedTrackStation.isPresent()) {
            return new ResponseEntity<>(selectedTrackStation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteStation(@PathVariable String id) {
        try {
            this.trackStationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
