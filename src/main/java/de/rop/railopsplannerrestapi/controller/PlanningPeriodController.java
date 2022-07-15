package de.rop.railopsplannerrestapi.controller;

import de.rop.railopsplannerrestapi.entity.PlanningPeriod;
import de.rop.railopsplannerrestapi.repository.PlanningPeriodRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api/planning-period")
public class PlanningPeriodController {
    private final PlanningPeriodRepository planningPeriodRepository;

    public PlanningPeriodController(PlanningPeriodRepository planningPeriodRepository) {
        this.planningPeriodRepository = planningPeriodRepository;
    }

    @GetMapping("")
    public List<PlanningPeriod> index() {
        return planningPeriodRepository.findAll();
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public PlanningPeriod newPlanningPeriod(@RequestBody PlanningPeriod newPlanningPeriod) {
        return planningPeriodRepository.save(newPlanningPeriod);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<PlanningPeriod> updatePlanningPeriod(@PathVariable("id") String id, @RequestBody PlanningPeriod planningPeriod) {
        Optional<PlanningPeriod> storedPlanningPeriod = planningPeriodRepository.findById(id);

        if (storedPlanningPeriod.isPresent()) {
            PlanningPeriod tempPlanningPeriod = storedPlanningPeriod.get();
            tempPlanningPeriod.setName(planningPeriod.getName());
            tempPlanningPeriod.setStart(planningPeriod.getStart());
            tempPlanningPeriod.setEnd(planningPeriod.getEnd());
            return new ResponseEntity<>(planningPeriodRepository.save(tempPlanningPeriod), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlanningPeriod> show(@PathVariable("id") String id) {
        Optional<PlanningPeriod> selectedPlanningPeriod = this.planningPeriodRepository.findById(id);
        if (selectedPlanningPeriod.isPresent()) {
            return new ResponseEntity<>(selectedPlanningPeriod.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code=HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable String id) {
        try {
            this.planningPeriodRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(e.getMessage());
        }
    }
}
