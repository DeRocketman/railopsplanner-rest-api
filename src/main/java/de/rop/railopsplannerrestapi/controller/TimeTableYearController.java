package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import de.rop.railopsplannerrestapi.repository.TimeTableYearRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/time_table_year")
public class TimeTableYearController {
    private TimeTableYearRepository timeTableYearRepository;

    public TimeTableYearController(TimeTableYearRepository timeTableYearRepository) {
        this.timeTableYearRepository = timeTableYearRepository;
    }

    @GetMapping("")
    public List<TimeTableYear> index() {
        return timeTableYearRepository.findAll();
    }

    @PostMapping(value = "/update_time_table_year")
    public ResponseEntity<TimeTableYear> updateTimeTableYear(@RequestBody TimeTableYear timeTableYear) {
        Optional<TimeTableYear> timeTableYearOptional = timeTableYearRepository.findById(timeTableYear.getId());

        if(!timeTableYearOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }

        timeTableYearRepository.save(timeTableYear);

        return ResponseEntity.ok(timeTableYear);
    }
}
