package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.List;

@Entity
@Getter
@Setter
public class MeasureReason extends IdentifiedEntity {
    @ManyToOne(targetEntity = Measure.class)
    @JoinColumn(name = "measure_ref")
    Measure measure;

    String reason;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name= "start_point")
    Station startPoint;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name="end_point")
    Station endPoint;

    String operatingMode;
    String start;
    String end;
    Boolean disturbed;
}
