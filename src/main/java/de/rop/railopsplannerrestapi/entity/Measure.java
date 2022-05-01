package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Measure extends IdentifiedEntity{
    @ManyToOne
    @JoinColumn(name = "planning_period_ref")
    PlanningPeriod planningPeriod;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rail_network_ref")
    RailNetwork railNetwork;

    String name;
    String start;
    String end;
    String stations;
}
