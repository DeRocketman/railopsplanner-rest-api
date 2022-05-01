package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
public class PlanningPeriod extends IdentifiedEntity{
    @ManyToOne
    @JoinColumn(name = "rail_network_ref")
    RailNetwork railNetwork;

    String name;
    String start;
    String end;

    @OneToMany(mappedBy = "planningPeriod")
    List<Measure> measureList;
}
