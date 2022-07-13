package de.rop.railopsplannerrestapi.service;

import de.rop.railopsplannerrestapi.entity.TimeTableYear;
import de.rop.railopsplannerrestapi.exception.ResourceNotFoundException;
import de.rop.railopsplannerrestapi.repository.TimeTableYearRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TimeTableYearService {
    private final TimeTableYearRepository timeTableYearRepository;

    public TimeTableYearService(TimeTableYearRepository timeTableYearRepository) {
        this.timeTableYearRepository = timeTableYearRepository;
    }

    public Page<TimeTableYear> findAll(Pageable pageable) {
        return timeTableYearRepository.findAll(pageable);
    }

    public TimeTableYear findById(String id) {
        Optional<TimeTableYear> timeTableYear = timeTableYearRepository.findById(id);

        if (timeTableYear.isEmpty()) {
            throw  new ResourceNotFoundException();
        }

        return timeTableYear.get();
    }
}
