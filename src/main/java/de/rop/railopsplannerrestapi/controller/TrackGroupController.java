package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.Track;
import de.rop.railopsplannerrestapi.entity.TrackGroup;
import de.rop.railopsplannerrestapi.entity.TrackStation;
import de.rop.railopsplannerrestapi.repository.TrackGroupRepository;
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
@RequestMapping("/api/track-group")
public class TrackGroupController {
    private final TrackGroupRepository trackGroupRepository;
    private final TrackRepository trackRepository;
    private final TrackStationRepository trackStationRepository;


    public TrackGroupController(TrackGroupRepository trackGroupRepository, TrackRepository trackRepository, TrackStationRepository trackStationRepository) {
        this.trackGroupRepository = trackGroupRepository;
        this.trackRepository = trackRepository;
        this.trackStationRepository = trackStationRepository;
    }

    @GetMapping("")
    public List<TrackGroup> index() {
        return trackGroupRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackGroup newTrackGroup(@RequestBody TrackGroup newTrackGroup) {
        TrackGroup tempTrackGroup = new TrackGroup();
        newTrackGroup.setId(tempTrackGroup.getId());
        tempTrackGroup = newTrackGroup;
        TrackGroup responseTrackGroup = trackGroupRepository.save(tempTrackGroup);
        if (!newTrackGroup.getTracks().isEmpty()) {
            for (Track track: newTrackGroup.getTracks()) {
                Track tempTrack = new Track();
                track.setId(tempTrack.getId());
                tempTrack = track;
                tempTrack.setTrackGroup(responseTrackGroup);
                Track responseTrack = trackRepository.save(tempTrack);

                for (TrackStation trackStation: tempTrack.getStations()) {
                    TrackStation tempStation = new TrackStation();
                    trackStation.setId(tempStation.getId());
                    tempStation = trackStation;
                    tempStation.setTrack(responseTrack);
                    trackStationRepository.save(tempStation);
                }
            }
        }
        return responseTrackGroup;
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TrackGroup> updateTrackGroup(@PathVariable("id") String id, @RequestBody TrackGroup trackGroup) {
        Optional<TrackGroup> storedTrackGroup = trackGroupRepository.findById(id);
        if (storedTrackGroup.isPresent()) {
            TrackGroup tempTrackGroup = storedTrackGroup.get();
            tempTrackGroup.setName(trackGroup.getName());

            return new ResponseEntity<>(trackGroupRepository.save(tempTrackGroup), HttpStatus.OK);
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
