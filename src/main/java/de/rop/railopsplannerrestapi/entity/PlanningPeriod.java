package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
public class PlanningPeriod extends IdentifiedEntity{

    @ManyToOne
    @JoinColumn(name = "time_table_year_ref")
    TimeTableYear timeTableYear;

    String name;
    String start;
    String end;
}
