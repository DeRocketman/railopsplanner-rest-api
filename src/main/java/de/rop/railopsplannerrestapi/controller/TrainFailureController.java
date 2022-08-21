package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.TrainFailure;
import de.rop.railopsplannerrestapi.repository.TrainFailureRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/train-failure")
public class TrainFailureController {
    private final TrainFailureRepository trainFailureRepository;

    public TrainFailureController(TrainFailureRepository trainFailureRepository) {
        this.trainFailureRepository = trainFailureRepository;
    }

    @GetMapping("")
    public List<TrainFailure> index() {
        return trainFailureRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TrainFailure newTrainFailure(@RequestBody TrainFailure trainFailure) {
        return trainFailureRepository.save(trainFailure);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TrainFailure> updateTrainFailure(@PathVariable("id") String id, @RequestBody TrainFailure trainFailure) {
        Optional<TrainFailure> storedTrainFailure = trainFailureRepository.findById(id);

        if (storedTrainFailure.isPresent()) {
            return new ResponseEntity<>(trainFailureRepository.save(trainFailure), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TrainFailure> show(@PathVariable("id") String id) {
        Optional<TrainFailure> selectedTrainFailure = this.trainFailureRepository.findById(id);
        if (selectedTrainFailure.isPresent()) {
            return new ResponseEntity<>(selectedTrainFailure.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        try {
            this.trainFailureRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
