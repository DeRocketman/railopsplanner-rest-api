package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.Measure;
import de.rop.railopsplannerrestapi.repository.MeasureRepository;
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

    @PostMapping("/update_measure")
    public ResponseEntity<Measure> updateMeasure(@RequestBody Measure measure) {
        Optional<Measure> measureOptional = measureRepository.findById(measure.getId());

        if (measureOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        measureRepository.save(measure);

        return ResponseEntity.ok(measure);
    }
}
