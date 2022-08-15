package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.PlanningPeriod;
import de.rop.railopsplannerrestapi.entity.RailNetwork;
import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import de.rop.railopsplannerrestapi.repository.PlanningPeriodRepository;
import de.rop.railopsplannerrestapi.repository.RailNetworkRepository;
import de.rop.railopsplannerrestapi.repository.TimeTableYearRepository;
import de.rop.railopsplannerrestapi.service.TimeTableYearService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/time-table-year")
public class TimeTableYearController {
    private final TimeTableYearService timeTableYearService;
    private final TimeTableYearRepository timeTableYearRepository;

    private final RailNetworkRepository railNetworkRepository;

    private final PlanningPeriodRepository planningPeriodRepository;


    public TimeTableYearController(TimeTableYearService timeTableYearService, TimeTableYearRepository timeTableYearRepository, RailNetworkRepository railNetworkRepository, PlanningPeriodRepository planningPeriodRepository) {
        this.timeTableYearService = timeTableYearService;
        this.timeTableYearRepository = timeTableYearRepository;
        this.railNetworkRepository = railNetworkRepository;
        this.planningPeriodRepository = planningPeriodRepository;
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
            return new ResponseEntity<>(timeTableYearRepository.save(timeTableYear), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TimeTableYear> show(@PathVariable String id) {
        return ResponseEntity.ok(timeTableYearService.findById(id));
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteTimeTableYear(@PathVariable String id) {
        try {
            this.timeTableYearRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
