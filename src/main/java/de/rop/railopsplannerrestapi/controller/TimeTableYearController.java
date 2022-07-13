package de.rop.railopsplannerrestapi.controller;


import de.rop.railopsplannerrestapi.entity.RailNetwork;
import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import de.rop.railopsplannerrestapi.repository.TimeTableYearRepository;
import de.rop.railopsplannerrestapi.service.TimeTableYearService;
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
    public TimeTableYear newTimeTableYear(@RequestBody TimeTableYear newTimeTableYear) {
        newTimeTableYear.setRailNetworksCounter(newTimeTableYear.getRailNetworks().size());
        newTimeTableYear.setPlanningPeriodsCounter(newTimeTableYear.getPlanningPeriods().size());
        for (RailNetwork railNetwork: newTimeTableYear.getRailNetworks()) {
            railNetwork.setMeasureCounter(0);
        }
        return timeTableYearRepository.save(newTimeTableYear);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<TimeTableYear> updateTimeTableYear(@PathVariable("id") String id, @RequestBody TimeTableYear timeTableYear) {
        Optional<TimeTableYear> storedTimeTableYear = timeTableYearRepository.findById(id);
        System.out.println("UpdateMethode fuer ID:" + id);

        if (storedTimeTableYear.isPresent()) {
            TimeTableYear tempTimeTable = storedTimeTableYear.get();
            tempTimeTable.setName(timeTableYear.getName());
            tempTimeTable.setFirstDate(timeTableYear.getFirstDate());
            tempTimeTable.setLastDate(timeTableYear.getLastDate());
            tempTimeTable.setRailNetworksCounter(timeTableYear.getRailNetworks().size());
            tempTimeTable.setPlanningPeriodsCounter(timeTableYear.getPlanningPeriods().size());
            return new ResponseEntity<>(timeTableYearRepository.save(tempTimeTable), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/{id}")
    public ResponseEntity<TimeTableYear> show(@PathVariable String id) {
        return ResponseEntity.ok(timeTableYearService.findById(id));
    }
}
