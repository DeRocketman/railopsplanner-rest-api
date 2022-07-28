package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.Track;
import de.rop.railopsplannerrestapi.entity.TrackStation;
import de.rop.railopsplannerrestapi.repository.TrackRepository;
import de.rop.railopsplannerrestapi.repository.TrackStationRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/track")
public class TrackController {
    private final TrackRepository trackRepository;
    private final TrackStationRepository trackStationRepository;

    public TrackController(TrackRepository trackRepository, TrackStationRepository trackStationRepository) {
        this.trackRepository = trackRepository;
        this.trackStationRepository = trackStationRepository;
    }

    @GetMapping("")
    public List<Track> index() {
        return trackRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Track newTrack(@RequestBody Track newTrack) {
        Track tempTrack = new Track();
        newTrack.setId(tempTrack.getId());
        tempTrack = newTrack;
        Track responseTrack = trackRepository.save(tempTrack);
        if (!newTrack.getStations().isEmpty()) {
            for (TrackStation trackStation: newTrack.getStations()) {
                TrackStation tempStation = new TrackStation();
                trackStation.setId(tempStation.getId());
                tempStation = trackStation;
                tempStation.setTrack(responseTrack);
                trackStationRepository.save(tempStation);
            }
        }
        System.out.println("tempTrack:" + tempTrack.getId());
        System.out.println("responseTrack" + responseTrack.getId());
        return responseTrack;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Track> updateTrack(@PathVariable("id") String id, @RequestBody Track track) {
        Optional<Track> storedTrack = trackRepository.findById(id);
        if (storedTrack.isPresent()) {
            Track tempTrack = storedTrack.get();
            tempTrack.setName(track.getName());

            return new ResponseEntity<>(trackRepository.save(tempTrack), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Track> show(@PathVariable("id") String id) {
        Optional<Track> selectedTrack = this.trackRepository.findById(id);
        if (selectedTrack.isPresent()) {
            return new ResponseEntity<>(selectedTrack.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteTrack(@PathVariable String id) {
        try {
            this.trackRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
