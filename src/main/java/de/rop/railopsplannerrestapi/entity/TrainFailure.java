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
    String trainNumber;
    String trainType;
    String startOperationControlPoint;
    String destinationOperationControlPoint;
    String failureFrom;
    String failureTo;
}
