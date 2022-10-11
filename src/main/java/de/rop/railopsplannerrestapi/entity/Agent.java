package de.rop.railopsplannerrestapi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
public class Agent extends IdentifiedEntity{

    @Column(unique = true)
    private String email;

    private String firstName;
    private String lastName;
    private String initials;
    private String phone;
    private String fax;

    @ManyToMany
    @JoinTable(
            name = "agent_measure",
            joinColumns = @JoinColumn(name = "agent_ref"),
            inverseJoinColumns = @JoinColumn(name = "measure_ref")
    )
    List<Measure> measures;

    public void addMeasure(Measure measure) {
        this.measures.add(measure);
        measure.addAgent(this);
    }
}
