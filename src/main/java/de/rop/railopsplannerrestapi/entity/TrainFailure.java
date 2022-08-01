package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class TrainFailure extends IdentifiedEntity{
    @ManyToOne
    @JoinColumn(name = "measure_ref")
    Measure measure;
    String trafficDay;
    String lineNumber;
    String trainNumber;
    String trainType;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "start_point")
    Station startPoint;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "end_point")
    Station endPoint;


    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "failure_from")
    Station failureFrom;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "failure_to")
    Station failureTo;
}
