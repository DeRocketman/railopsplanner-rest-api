package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import de.rop.railopsplannerrestapi.repository.TimeTableYearRepository;
import de.rop.railopsplannerrestapi.service.TimeTableYearService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/time-table-year")
public class TimeTableYearController {
    private final TimeTableYearService timeTableYearService;
    private final TimeTableYearRepository timeTableYearRepository;

    public TimeTableYearController(TimeTableYearService tableYearService, TimeTableYearRepository timeTableYearRepository) {
        this.timeTableYearService = tableYearService;
        this.timeTableYearRepository = timeTableYearRepository;
    }

    @GetMapping("")
    public List<TimeTableYear> index() {
        return timeTableYearRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public TimeTableYear newTimeTableYear(@RequestBody TimeTableYear requestedTimeTableYear) {
        return timeTableYearRepository.save(requestedTimeTableYear);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<TimeTableYear> updateTimeTableYear(@PathVariable("id") String id, @RequestBody TimeTableYear timeTableYear) {
        Optional<TimeTableYear> storedTimeTableYear = timeTableYearRepository.findById(id);

        if (storedTimeTableYear.isPresent()) {
            TimeTableYear tempTimeTable = storedTimeTableYear.get();
            tempTimeTable.setName(timeTableYear.getName());
            tempTimeTable.setFirstDate(timeTableYear.getFirstDate());
            tempTimeTable.setLastDate(timeTableYear.getLastDate());
            return new ResponseEntity<>(timeTableYearRepository.save(tempTimeTable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TimeTableYear> show(@PathVariable String id) {
        return ResponseEntity.ok(timeTableYearService.findById(id));
    }
    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteTimeTableYear(@PathVariable String id) {
        try {
            this.timeTableYearRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
