package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.*;
import de.rop.railopsplannerrestapi.repository.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/track-group")
public class TrackGroupController {
    private final TrackGroupRepository trackGroupRepository;
    private final TrackRepository trackRepository;
    private final TrackStationRepository trackStationRepository;

    private final CrossStationRepository crossStationRepository;
    private final StartEndStationRepository startEndStationRepository;

    public TrackGroupController(TrackGroupRepository trackGroupRepository, TrackRepository trackRepository,
                                TrackStationRepository trackStationRepository,
                                CrossStationRepository crossStationRepository,
                                StartEndStationRepository startEndStationRepository) {
        this.trackGroupRepository = trackGroupRepository;
        this.trackRepository = trackRepository;
        this.trackStationRepository = trackStationRepository;
        this.crossStationRepository = crossStationRepository;
        this.startEndStationRepository = startEndStationRepository;
    }

    @GetMapping("")
    public List<TrackGroup> index() {
        return trackGroupRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackGroup newTrackGroup(@RequestBody TrackGroup newTrackGroup) {
        TrackGroup createdTrackGroup = this.trackGroupRepository.save(newTrackGroup);
        for (Track createTrack: newTrackGroup.getTracks()) {
            Track createdTrack = this.trackRepository.save(createTrack);
            createdTrackGroup.addTrack(createdTrack);
            StartEndStation startPoint = this.startEndStationRepository.save(createdTrack.getStartPoint());
            createdTrack.setStartPoint(startPoint);
            StartEndStation endPoint = this.startEndStationRepository.save(createdTrack.getEndPoint());
            createdTrack.setEndPoint(endPoint);
            for (CrossStation crossStation: createdTrack.getCrossStations()) {
                CrossStation storedCrossStation = this.crossStationRepository.save(crossStation);
                createdTrack.addCrossStation(storedCrossStation);
            }
        }
        return newTrackGroup;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TrackGroup> updateTrackGroup(@PathVariable("id") String id, @RequestBody TrackGroup trackGroup) {
        Optional<TrackGroup> storedTrackGroup = trackGroupRepository.findById(id);
        if (storedTrackGroup.isPresent()) {
            return new ResponseEntity<>(trackGroupRepository.save(trackGroup), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrackGroup> show(@PathVariable("id") String id) {
        Optional<TrackGroup> selectedTrackGroup = this.trackGroupRepository.findById(id);
        if (selectedTrackGroup.isPresent()) {
            return new ResponseEntity<>(selectedTrackGroup.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteTrackGroup(@PathVariable String id) {
        try {
            this.trackGroupRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
