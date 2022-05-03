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
    @ManyToOne
    @JoinColumn(name = "measure_ref")
    Measure measure;

    String reason;
    String startOperationControlPoint;
    String endOperationControlPoint;
    String operatingMode;
    String start;
    String end;
    Boolean disturbed;
}
