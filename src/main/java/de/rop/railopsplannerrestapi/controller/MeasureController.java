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
@RequestMapping("/api/measure")
public class MeasureController {

    private final MeasureRepository measureRepository;
    private final StationRepository stationRepository;
    private final ScheduleDeviationRepository deviationRepository;
    private final TrainFailureRepository trainFailureRepository;
    private final UserRepository userRepository;
    private final MeasureReasonRepository measureReasonRepository;

    public MeasureController(MeasureRepository measureRepository, StationRepository stationRepository,
                             ScheduleDeviationRepository deviationRepository,
                             TrainFailureRepository trainFailureRepository, UserRepository userRepository, MeasureReasonRepository measureReasonRepository) {
        this.measureRepository = measureRepository;
        this.stationRepository = stationRepository;
        this.deviationRepository = deviationRepository;
        this.trainFailureRepository = trainFailureRepository;
        this.userRepository = userRepository;
        this.measureReasonRepository = measureReasonRepository;
    }

    @GetMapping("")
    public List<Measure> index() {
        return measureRepository.findAll();
    }
    @PostMapping("/create")
    public ResponseEntity<Measure> newMeasure(@RequestBody Measure measure) {
            Measure createMeasure = this.measureRepository.save(measure);
            for (Agent agentsToUpdate: createMeasure.getAgents()) {
                createMeasure.addAgent(agentsToUpdate);
            }
            if (measure.getTrainFailures() != null) {
                for (TrainFailure trainFailure: measure.getTrainFailures()) {
                    trainFailureRepository.save(trainFailure);
                    createMeasure.addTrainFailure(trainFailure);
                }
            }
            if (measure.getScheduleDeviations() != null) {
                for (ScheduleDeviation scheduleDeviation: measure.getScheduleDeviations()) {
                    deviationRepository.save(scheduleDeviation);
                    createMeasure.addScheduleDeviation(scheduleDeviation);
                }
            }
            if (measure.getReasons() != null) {
                for (MeasureReason measureReason: measure.getReasons()) {
                    measureReasonRepository.save(measureReason);
                    createMeasure.addMeasureReason(measureReason);
                }
            }
            return new ResponseEntity<>(createMeasure, HttpStatus.OK);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<Measure> updateMeasure(@PathVariable("id") String id,@RequestBody Measure measure) {
        Optional<Measure> measureOptional = measureRepository.findById(id);
        if (measureOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(measureRepository.save(measure), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Measure> show(@PathVariable("id") String id) {
        Optional<Measure> selectedMeasure = this.measureRepository.findById(id);
        if (selectedMeasure.isPresent()) {
            return new ResponseEntity<>(selectedMeasure.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        try {
            this.measureRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
