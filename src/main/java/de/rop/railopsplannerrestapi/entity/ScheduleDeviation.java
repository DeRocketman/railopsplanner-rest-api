package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ScheduleDeviation extends IdentifiedEntity{
    @ManyToOne
    @JoinColumn(name = "measure_ref")
    Measure measure;
    String trafficDay;
    String trainNumber;
    String trainType;
    String startOperationControlPoint;
    String destinationOperationControlPoint;
    String deviationFromOperationalControlPoint;
    String deviationType;
    Integer time;
}
