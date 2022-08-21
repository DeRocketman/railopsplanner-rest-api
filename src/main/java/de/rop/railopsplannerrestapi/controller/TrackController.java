package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.CrossStation;
import de.rop.railopsplannerrestapi.entity.StartEndStation;
import de.rop.railopsplannerrestapi.entity.Track;
import de.rop.railopsplannerrestapi.entity.TrackStation;
import de.rop.railopsplannerrestapi.repository.*;
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
    private final TrackGroupRepository trackGroupRepository;
    private final CrossStationRepository crossStationRepository;
    private final StartEndStationRepository startEndStationRepository;

    public TrackController(TrackRepository trackRepository, TrackStationRepository trackStationRepository, TrackGroupRepository trackGroupRepository, CrossStationRepository crossStationRepository, StartEndStationRepository startEndStationRepository) {
        this.trackRepository = trackRepository;
        this.trackStationRepository = trackStationRepository;
        this.trackGroupRepository = trackGroupRepository;
        this.crossStationRepository = crossStationRepository;
        this.startEndStationRepository = startEndStationRepository;
    }

    @GetMapping("")
    public List<Track> index() {
        return trackRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Track> newTrack(@RequestBody Track newTrack) {
        Track createTrack = this.trackRepository.save(newTrack);
        StartEndStation startPoint = this.startEndStationRepository.save(newTrack.getStartPoint());
        createTrack.setStartPoint(startPoint);
        StartEndStation endPoint = this.startEndStationRepository.save(newTrack.getEndPoint());
        createTrack.setEndPoint(endPoint);
        for (CrossStation crossStation: newTrack.getCrossStations()) {
            CrossStation storedCrossStation = this.crossStationRepository.save(crossStation);
            createTrack.addCrossStation(storedCrossStation);
        }
        return new ResponseEntity<>(createTrack, HttpStatus.CREATED);
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
