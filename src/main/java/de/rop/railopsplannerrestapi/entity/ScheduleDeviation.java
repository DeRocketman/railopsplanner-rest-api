package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class ScheduleDeviation extends IdentifiedEntity{
    String trafficDay;
    String line;
    String trainType;
    String trainNumber;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "start_point")
    Station startPoint;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "end_point")
    Station endPoint;

    String deviationType;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "deviation_start_point")
    Station deviationStartPoint;

    Integer time;
    @ManyToOne
    @JoinColumn(name = "measure_ref")
    Measure measure;
}
