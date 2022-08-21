package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Measure extends IdentifiedEntity{

    String name;
    String start;
    String end;
    String effect;
    String passengerConcept;
    Integer lossKilometer;
    String kigbauNumber;
    String responseDate;
    String measureKind;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "start_point")
    Station startPoint;

    @ManyToOne(targetEntity = Station.class)
    @JoinColumn(name = "end_Point")
    Station endPoint;

    @OneToMany(mappedBy = "measure")
    @JsonIgnoreProperties({"measure"})
    List<MeasureReason> reasons;

    @OneToMany(mappedBy = "measure")
    @JsonIgnoreProperties({"measure"})
    List<TrainFailure> trainFailures;

    @OneToMany(mappedBy = "measure")
    @JsonIgnoreProperties({"measure"})
    List<ScheduleDeviation> scheduleDeviations;

    @ManyToMany(mappedBy = "measures")
    @JsonIgnoreProperties({"measures"})
    Set<Agent> agents;

    @ManyToMany(mappedBy = "measures")
    List<User> clerks;

    @OneToMany(mappedBy = "measure")
    @JsonIgnoreProperties({"measure"})
    List<ToDoItem> toDoItems;

    @ManyToOne(targetEntity = RailNetwork.class)
    @JoinColumn(name = "rail_network_ref")
    RailNetwork railNetwork;

    public void addAgent(Agent agent) {
        agents.add(agent);
        agent.getMeasures().add(this);
    }

    public void addClerk(User clerk) {
        clerks.add(clerk);
        clerk.getMeasures().add(this);
    }

    public void addMeasureReason(MeasureReason measureReason) {
        reasons.add(measureReason);
        measureReason.setMeasure(this);
    }

    public void addTrainFailure(TrainFailure trainFailure) {
        trainFailures.add(trainFailure);
        trainFailure.setMeasure(this);
    }
    public void addScheduleDeviation(ScheduleDeviation scheduleDeviation) {
        scheduleDeviations.add(scheduleDeviation);
        scheduleDeviation.setMeasure(this);
    }
}
