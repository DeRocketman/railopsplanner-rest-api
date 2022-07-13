package de.rop.railopsplannerrestapi.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Measure extends IdentifiedEntity{
    @ManyToOne
    @JoinColumn(name = "rail_network_ref")
    RailNetwork railNetwork;

    String name;
    String start;
    String end;
    String stations;

    @OneToMany(mappedBy = "measure")
    List<MeasureReason> reasonList;

    String effect;
    String passengerConcept;
    Integer lossKilometer;
    String kigbauNumber;

    @OneToMany(mappedBy = "measure")
    List<TrainFailure> trainFailures;

    @OneToMany(mappedBy = "measure")
    List<ScheduleDeviation> scheduleDeviations;

    @ManyToMany(mappedBy = "measures")
    List<Agent> agents;

    String responseDate;
    String measureKind;

    @ManyToMany(mappedBy = "measures")
    List<User> clerks;

    @OneToMany(mappedBy = "measure")
    List<ToDoItem> toDoItems;
}
