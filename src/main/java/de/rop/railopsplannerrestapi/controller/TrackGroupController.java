package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.TrackGroup;
import de.rop.railopsplannerrestapi.repository.TrackGroupRepository;
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

    public TrackGroupController(TrackGroupRepository trackGroupRepository) {
        this.trackGroupRepository = trackGroupRepository;
    }

    @GetMapping("")
    public List<TrackGroup> index() {
        return trackGroupRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TrackGroup newTrackGroup(@RequestBody TrackGroup newTrackGroup) {
        return trackGroupRepository.save(newTrackGroup);
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
