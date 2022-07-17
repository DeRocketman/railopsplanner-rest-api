package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.Line;
import de.rop.railopsplannerrestapi.repository.LineRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/line")
public class LineController {
    private final LineRepository lineRepository;

    public LineController(LineRepository lineRepository) {
        this.lineRepository = lineRepository;
    }

    @GetMapping("")
    public List<Line> index() {
        return lineRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Line newLine(@RequestBody Line newLine) {
        return lineRepository.save(newLine);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Line> updateLine(@PathVariable("id") String id, @RequestBody Line line) {
        Optional<Line> storedLine = lineRepository.findById(id);
        if (storedLine.isPresent()) {
            Line tempLine = storedLine.get();
            tempLine.setName(line.getName());

            return new ResponseEntity<>(lineRepository.save(tempLine), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Line> show(@PathVariable("id") String id) {
        Optional<Line> selectedLine = this.lineRepository.findById(id);
        if (selectedLine.isPresent()) {
            return new ResponseEntity<>(selectedLine.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteLine(@PathVariable String id) {
        try {
            this.lineRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
