package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.ScheduleDeviation;
import de.rop.railopsplannerrestapi.repository.ScheduleDeviationRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/schedule-deviation")
public class ScheduleDeviationController {
    private final ScheduleDeviationRepository deviationRepository;

    public ScheduleDeviationController(ScheduleDeviationRepository deviationRepository) {
        this.deviationRepository = deviationRepository;
    }

    @GetMapping("")
    public List<ScheduleDeviation> index() {
        return deviationRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ScheduleDeviation newScheduleDeviation(@RequestBody ScheduleDeviation newDeviation) {
        return deviationRepository.save(newDeviation);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<ScheduleDeviation> updateDeviation(@PathVariable("id") String id, @RequestBody ScheduleDeviation scheduleDeviation) {
        Optional<ScheduleDeviation> storedDeviation = deviationRepository.findById(id);

        if (storedDeviation.isPresent()) {
            return new ResponseEntity<>(deviationRepository.save(scheduleDeviation), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDeviation> show(@PathVariable("id") String id) {
        Optional<ScheduleDeviation> selectedDeviation = this.deviationRepository.findById(id);
        if (selectedDeviation.isPresent()) {
            return new ResponseEntity<>(selectedDeviation.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        try {
            this.deviationRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
