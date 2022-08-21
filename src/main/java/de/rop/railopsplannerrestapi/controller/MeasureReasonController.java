package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.MeasureReason;
import de.rop.railopsplannerrestapi.repository.MeasureReasonRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/measure-reason")
public class MeasureReasonController {
    private final MeasureReasonRepository measureReasonRepository;

    public MeasureReasonController(MeasureReasonRepository measureReasonRepository) {
        this.measureReasonRepository = measureReasonRepository;
    }

    @GetMapping("")
    public List<MeasureReason> index() {
        return measureReasonRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public MeasureReason newMeasureReason(@RequestBody MeasureReason newMeasureReason) {
        return measureReasonRepository.save(newMeasureReason);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<MeasureReason> updateMeasureReason(@PathVariable("id") String id, @RequestBody MeasureReason measureReason) {
        Optional<MeasureReason> storedMeasureReason = measureReasonRepository.findById(id);

        if (storedMeasureReason.isPresent()) {
            return new ResponseEntity<>(measureReasonRepository.save(measureReason), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MeasureReason> show(@PathVariable("id") String id) {
        Optional<MeasureReason> selectedMeasureReason = this.measureReasonRepository.findById(id);
        if (selectedMeasureReason.isPresent()) {
            return new ResponseEntity<>(selectedMeasureReason.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        try {
            this.measureReasonRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
