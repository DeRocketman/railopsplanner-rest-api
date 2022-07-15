package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.Measure;
import de.rop.railopsplannerrestapi.entity.Measure;
import de.rop.railopsplannerrestapi.entity.PlanningPeriod;
import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import de.rop.railopsplannerrestapi.repository.MeasureRepository;
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

    private MeasureRepository measureRepository;

    public MeasureController(MeasureRepository measureRepository) {
        this.measureRepository = measureRepository;
    }

    @GetMapping("")
    public List<Measure> index() {
        return measureRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Measure newTimeTableYear(@RequestBody Measure measure) {
        return measureRepository.save(measure);
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
